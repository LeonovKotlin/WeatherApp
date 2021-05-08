package com.example.weatherapp.network.provider

import com.example.weatherapp.db.entities.current.CurrentWeatherResponse
import com.example.weatherapp.db.entities.current.WeatherLocation
import com.example.weatherapp.db.unitlocalized.location.CurrentWeatherLocation

class LocationPrividerImpl : LocationPrivider {
    override suspend fun locationChanged(lastWeatherLocation: WeatherLocation): Boolean {
        return true
    }

    override suspend fun getLocationStr(): String {
        return "Gomel"
    }
}