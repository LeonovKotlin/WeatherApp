package com.example.weatherapp.network.provider

import com.example.weatherapp.db.entities.current.CurrentWeatherResponse

interface LocationPrivider {
    suspend fun haslocationChanged(lastWeatherLocation: CurrentWeatherResponse): Boolean
    suspend fun getLocationStr(): String


}