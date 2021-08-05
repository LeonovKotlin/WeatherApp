package com.example.weatherapp.network.provider

import com.example.weatherapp.network.response.CurrentWeatherResponse

interface LocationPrivider {
    suspend fun haslocationChanged(lastWeatherLocation: CurrentWeatherResponse): Boolean
    suspend fun getLocationStr(): String


}