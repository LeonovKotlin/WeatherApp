package com.example.weatherapp.network

import androidx.lifecycle.LiveData

interface WeatherNetDataSource {
    val downloadedCurrentWeather: LiveData<WeatherCurrentResponse>
    suspend fun fetchCurrentWeather(
  location:String
    )
}