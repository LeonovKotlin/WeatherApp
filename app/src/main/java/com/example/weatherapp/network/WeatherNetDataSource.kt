package com.example.weatherapp.network

import androidx.lifecycle.LiveData
import com.example.weatherapp.db.entities.current.CurrentWeatherResponse
import com.example.weatherapp.db.entities.future.FutureWeatherResponse

interface WeatherNetDataSource {
    val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>
    val downloadedFutureWeather: LiveData<FutureWeatherResponse>

    suspend fun fetchCurrentWeather(
  location:String
    )
    suspend fun fetchFutureWeather(
  location:String
    )
}