package com.example.weatherapp.repository

import androidx.lifecycle.LiveData
import com.example.weatherapp.db.CurrentWeatherDao
import com.example.weatherapp.db.FutureWeatherDao
import com.example.weatherapp.db.WeatherLocDao
import com.example.weatherapp.network.response.CurrentWeatherResponse
import com.example.weatherapp.network.response.FutureWeatherResponse
import com.example.weatherapp.db.unitlocalized.current.UnitSpeceficCurrentWeather
import com.example.weatherapp.db.unitlocalized.future.SpeceficFutureWeather
import com.example.weatherapp.network.WeatherNetDataSource
import com.example.weatherapp.network.provider.LocationPrivider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.ZonedDateTime
import java.util.*

class ForecastRepositoryImpl(
        private val currentWeatherDao: CurrentWeatherDao,
        private val futureWeatherDao: FutureWeatherDao,
        private val weatherLocDao: WeatherLocDao,
        private val weatherNetDataSource: WeatherNetDataSource,
        private val locationProvider: LocationPrivider
): ForecastRepository {
    init {
        weatherNetDataSource.apply {
            downloadedCurrentWeather.observeForever { newCurrentWeather ->
                persistFechedCurrentWeather(newCurrentWeather)
            }
            downloadedFutureWeather.observeForever { newFutureWeather ->
                persistFechedFutureWeather(newFutureWeather)
            }
        }
    }
    override suspend fun getCurrentWeather(): LiveData<out UnitSpeceficCurrentWeather> {
        return withContext(Dispatchers.IO) {
            initWeatherData()
            return@withContext currentWeatherDao.getWeatherMetric()

        }
    }
    override suspend fun getFutureWeatherList(): LiveData<out List<SpeceficFutureWeather>> {
        return withContext(Dispatchers.IO) {
            initWeatherData()
            return@withContext futureWeatherDao.getFutureWeatherMetric()
        }
    }

    override suspend fun getWeatherLocation(): LiveData<CurrentWeatherResponse> {
        return withContext(Dispatchers.IO) {
            return@withContext weatherLocDao.getLocation()
        }
    }
    private fun persistFechedCurrentWeather(fetchedWeather: CurrentWeatherResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            currentWeatherDao.upsert(fetchedWeather.currentWeather!!)
            weatherLocDao.upsert(fetchedWeather)
        }
    }
    private fun persistFechedFutureWeather(fetchedWeather: FutureWeatherResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            val futureWeatherList = fetchedWeather.entries
            futureWeatherDao.insert(futureWeatherList!!)
            weatherLocDao.upsert(fetchedWeather.location!!)
        }
    }
    private suspend fun initWeatherData() {
        val lastWeatherLocation = weatherLocDao.getLocationNonLive()

        if (lastWeatherLocation == null
                || locationProvider.haslocationChanged(lastWeatherLocation)) {
            fetchCurrentWeather()
            fetchFutureWeather()
            return
        }
        if (isFetchCurrentNeeded(ZonedDateTime.now().minusSeconds(3)))
            fetchCurrentWeather()
        if (isFetchFutureNeeded())
            fetchFutureWeather()
    }
    private suspend fun fetchCurrentWeather() {
        weatherNetDataSource.fetchCurrentWeather(locationProvider.getLocationStr())
    }
    private suspend fun fetchFutureWeather() {
        weatherNetDataSource.fetchFutureWeather(locationProvider.getLocationStr())
    }
    private fun isFetchCurrentNeeded(lastFetchTime: ZonedDateTime): Boolean {
        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(thirtyMinutesAgo)
    }
    private fun isFetchFutureNeeded(): Boolean {
        val futureWeatherCount = futureWeatherDao.countFutureWeather()
        return  futureWeatherCount < 15
    }
}