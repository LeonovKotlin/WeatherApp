package com.example.weatherapp.repository

import androidx.lifecycle.LiveData
import com.example.weatherapp.db.CurrentWeatherDao
import com.example.weatherapp.db.unitlocalized.UnitSpeceficCurrentWeather
import com.example.weatherapp.network.WeatherCurrentResponse
import com.example.weatherapp.network.WeatherNetDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.ZonedDateTime
import java.util.*

class ForecastRepositoryImpl(
        private val currentWeatherDao: CurrentWeatherDao,
        private val weatherNetDataSource: WeatherNetDataSource
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
    private fun persistFechedCurrentWeather(fetchedWeather: WeatherCurrentResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            currentWeatherDao.upsert(fetchedWeather.main)
        }
    }
    private suspend fun initWeatherData() {
        if (if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                isFetchCurrentNeeded(ZonedDateTime.now().minusHours(1))
            } else {
                TODO("VERSION.SDK_INT < O")
            }
        )
            fetchCurrentWeather()
    }
    private suspend fun fetchCurrentWeather() {
        weatherNetDataSource.fetchCurrentWeather("GOMEL")////
    }
//    private fun isFetchCurrentNeeded(lastFetchTime: ZonedDateTime) : Boolean {
//        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
//        return  lastFetchTime.isBefore(thirtyMinutesAgo)
//    }
    private fun isFetchCurrentNeeded(lastFetchTime: ZonedDateTime): Boolean {
//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val thirtyMinutesAgo = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            ZonedDateTime.now().minusMinutes(30)
        } else {
            TODO("VERSION.SDK_INT < O")
        }
    return lastFetchTime.isBefore(thirtyMinutesAgo)
    }
}