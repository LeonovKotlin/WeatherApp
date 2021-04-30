package com.example.weatherapp.network

import androidx.lifecycle.LiveData
import retrofit2.http.Query

interface WeatherNetDataSource {
    val downloadedCurrentWeather: LiveData<WeatherHistory>
    suspend fun fetchCurrentWeather(
  lat:Double,
  lon:Double,
  period:Long
    )
}