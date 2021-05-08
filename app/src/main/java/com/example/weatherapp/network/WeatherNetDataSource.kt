package com.example.weatherapp.network

import androidx.lifecycle.LiveData
import com.example.weatherapp.db.entities.current.CurrentWeatherResponse

interface WeatherNetDataSource {
    val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>
    suspend fun fetchCurrentWeather(
  location:String
    )
}