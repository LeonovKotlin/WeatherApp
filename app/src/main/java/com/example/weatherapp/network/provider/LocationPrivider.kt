package com.example.weatherapp.network.provider

import com.example.weatherapp.db.entities.current.CurrentWeatherResponse
import com.example.weatherapp.db.entities.current.WeatherLocation
import com.example.weatherapp.db.unitlocalized.location.CurrentWeatherLocation

interface LocationPrivider {
    suspend fun locationChanged(lastWeatherLocation: WeatherLocation): Boolean
    suspend fun getLocationStr():String
}