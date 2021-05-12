package com.example.weatherapp.network.provider

import com.example.weatherapp.db.entities.future.WeatherLocation

interface LocationPrivider {
    suspend fun haslocationChanged(lastWeatherLocation: WeatherLocation): Boolean
    suspend fun getLocationStr(): String
}