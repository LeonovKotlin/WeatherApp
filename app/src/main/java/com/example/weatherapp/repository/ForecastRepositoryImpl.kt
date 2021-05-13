package com.example.weatherapp.repository

import androidx.lifecycle.LiveData
import com.example.weatherapp.db.CurrentWeatherDao
import com.example.weatherapp.db.FutureWeatherDao
import com.example.weatherapp.db.WeatherLocDao
import com.example.weatherapp.db.entities.current.CurrentWeatherResponse
import com.example.weatherapp.db.entities.future.FutureWeatherResponse
import com.example.weatherapp.db.entities.future.WeatherLocation
import com.example.weatherapp.db.unitlocalized.UnitSpeceficCurrentWeather
import com.example.weatherapp.db.unitlocalized.future.SpeceficFutureWeather
import com.example.weatherapp.network.WeatherNetDataSource
import com.example.weatherapp.network.provider.LocationPrivider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
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
    override suspend fun getCurrentWeather(metric: Boolean): LiveData<out UnitSpeceficCurrentWeather> {
        return withContext(Dispatchers.IO) {
            initWeatherData()
            return@withContext if (metric) currentWeatherDao.getWeatherMetric()
            else currentWeatherDao.getWeatherImperial()
        }
    }
    override suspend fun getFutureWeatherList(
            startDate: LocalDate, metric: Boolean): LiveData<out List<SpeceficFutureWeather>> {
        return withContext(Dispatchers.IO) {
            initWeatherData()
            return@withContext if (metric) futureWeatherDao.getFutureWeatherMetric(startDate)
            else futureWeatherDao.getFutureWeatherImperial(startDate)
        }
    }
    override suspend fun getWeatherLocation(): LiveData<WeatherLocation> {
        return withContext(Dispatchers.IO) {
            return@withContext weatherLocDao.getLocation()
        }
    }
    private fun persistFechedCurrentWeather(fetchedWeather: CurrentWeatherResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            currentWeatherDao.upsert(fetchedWeather.currentWeather!!)
            //weatherLocDao.upsert(fetchedWeather.location)//location

        }
    }
    private fun persistFechedFutureWeather(fetchedWeather: FutureWeatherResponse) {
//        weatherLocDao.upsert(fetchedWeather.location)//location
        fun deleteOldForecastData() {
            val today = LocalDate.now()
            futureWeatherDao.deleteOldEntries(today)
        }
        GlobalScope.launch(Dispatchers.IO) {
            deleteOldForecastData()
            val futureWeatherList = fetchedWeather.entries
            futureWeatherDao.insert(futureWeatherList)
            weatherLocDao.upsert(fetchedWeather.location)
//            weatherLocDao.upsert(fetchedWeather)
        }
    }

    private suspend fun initWeatherData() {
        val lastWeatherLocation = weatherLocDao.getLocationNonLive()
            if(lastWeatherLocation == null
                    || locationProvider.haslocationChanged(lastWeatherLocation)) {
                fetchCurrentWeather()
                fetchFutureWeather()

                return
            }
             if (isFetchCurrentNeeded(ZonedDateTime.now().minusHours(1)))
             fetchCurrentWeather()

        if (isFetchFutureNeeded())
            fetchFutureWeather()

//                     if (isFetchCurrentNeeded(lastWeatherLocation.zonedDateTime))
//                         fetchCurrentWeather()
    }
    //
    private suspend fun fetchCurrentWeather() {
        weatherNetDataSource.fetchCurrentWeather(locationProvider.getLocationStr()) ////
    }

    private suspend fun fetchFutureWeather() {
        weatherNetDataSource.fetchFutureWeather(locationProvider.getLocationStr()) ////
    }
    private fun isFetchCurrentNeeded(lastFetchTime: ZonedDateTime): Boolean {
    val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
    return lastFetchTime.isBefore(thirtyMinutesAgo)
}
    private fun isFetchFutureNeeded(): Boolean {
        val today = LocalDateTime.now()
        val futureWeatherCount = futureWeatherDao.countFutureWeather(today)
        return futureWeatherCount < FORECAST_DAYS_COUNT
    }
}
//    if (isFetchCurrentNeeded(ZonedDateTime.now().minusHours(1)))
//    val now = Clock.System.now()
//    private fun isFetchCurrentNeeded(lastFetchTime: LocalDateTime) : Boolean {
//        val now = Clock.System.now()
//        val systemTZ = kotlinx.datetime.TimeZone.currentSystemDefault()
//        val tomorrow = now.minus(2, DateTimeUnit.DAY, systemTZ)
//        val threeYearsAndAMonthLater = now.minus(DateTimePeriod(minutes = 30), systemTZ)

