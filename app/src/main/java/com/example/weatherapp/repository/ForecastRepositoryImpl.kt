package com.example.weatherapp.repository

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import com.example.weatherapp.db.CurrentWeatherDao
import com.example.weatherapp.db.unitlocalized.UnitSpeceficCurrentWeather
import com.example.weatherapp.network.WeatherHistory
import com.example.weatherapp.network.WeatherNetDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class ForecastRepositoryImpl(
        private val currentWeaherDao: CurrentWeatherDao,
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
            return@withContext if (metric) currentWeaherDao.getWeatherMetric()
            else currentWeaherDao.getWeatherImperial()
        }
    }
    private fun persistFechedCurrentWeather(fetchedWeather: WeatherHistory) {
        GlobalScope.launch(Dispatchers.IO) {
            currentWeaherDao.upsert(fetchedWeather.current)
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
        weatherNetDataSource.fetchCurrentWeather(53.9, 27.56,1619481600)
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