package com.example.weatherapp.network.provider

import com.example.weatherapp.db.entities.futures.WeatherLocation

interface LocationPrivider {
    suspend fun LocationChanged(lastWeatherLocation: WeatherLocation): Boolean
    suspend fun getLocationStr():String
}