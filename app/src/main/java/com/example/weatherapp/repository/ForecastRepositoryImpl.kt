package com.example.weatherapp.repository

import androidx.lifecycle.LiveData
import com.example.weatherapp.db.CurrentWeatherDao
import com.example.weatherapp.db.WeatherLocDao
import com.example.weatherapp.db.entities.current.CurrentWeatherResponse
import com.example.weatherapp.db.entities.current.WeatherLocation
import com.example.weatherapp.db.unitlocalized.UnitSpeceficCurrentWeather
import com.example.weatherapp.network.WeatherNetDataSource
import com.example.weatherapp.network.provider.LocationPrivider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.datetime.*
import org.threeten.bp.ZonedDateTime
import java.util.*

class ForecastRepositoryImpl(
        private val currentWeatherDao: CurrentWeatherDao,
        private val weatherLocDao: WeatherLocDao,
        private val weatherNetDataSource: WeatherNetDataSource,
        private val locationProvider: LocationPrivider
): ForecastRepository {
    init {
        weatherNetDataSource.downloadedCurrentWeather.observeForever { newCurrentWeather ->
            persistFechedCurrentWeather(newCurrentWeather)
        }
    }
    override suspend fun getCurrentWeather(metric: Boolean): LiveData<out UnitSpeceficCurrentWeather> {
        return withContext(Dispatchers.IO) {
            initWeatherData()
            return@withContext if (metric) currentWeatherDao.getWeatherMetric()
            else currentWeatherDao.getWeatherImperial()
        }
    }
    override suspend fun getWeatherLocation(): LiveData<WeatherLocation> {
        return withContext(Dispatchers.IO) {
            return@withContext weatherLocDao.getLocation()
        }
    }
    private fun persistFechedCurrentWeather(fetchedWeather: CurrentWeatherResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            currentWeatherDao.upsert(fetchedWeather)
            weatherLocDao.upsert(fetchedWeather.weatherLocation!!)
        }
    }
    private suspend fun initWeatherData() {
        val lastWeatherLocation = weatherLocDao.getLocation().value
            if(lastWeatherLocation == null || locationProvider.locationChanged(lastWeatherLocation)) {
                fetchCurrentWeather()
                return
            }
             if (isFetchCurrentNeeded(ZonedDateTime.now().minusHours(1)))
             fetchCurrentWeather()
    }
    //
    private suspend fun fetchCurrentWeather() {
        weatherNetDataSource.fetchCurrentWeather(locationProvider.getLocationStr())
    }
private fun isFetchCurrentNeeded(lastFetchTime: ZonedDateTime): Boolean {
    val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
    return lastFetchTime.isBefore(thirtyMinutesAgo)
}
}
//    if (isFetchCurrentNeeded(ZonedDateTime.now().minusHours(1)))
//    val now = Clock.System.now()
//    private fun isFetchCurrentNeeded(lastFetchTime: LocalDateTime) : Boolean {
//        val now = Clock.System.now()
//        val systemTZ = kotlinx.datetime.TimeZone.currentSystemDefault()
//        val tomorrow = now.minus(2, DateTimeUnit.DAY, systemTZ)
//        val threeYearsAndAMonthLater = now.minus(DateTimePeriod(minutes = 30), systemTZ)

