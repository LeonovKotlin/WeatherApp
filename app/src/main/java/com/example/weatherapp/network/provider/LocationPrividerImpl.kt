package com.example.weatherapp.network.provider

import com.example.weatherapp.db.entities.current.Coord

class LocationPrividerImpl : LocationPrivider {
    override suspend fun locationChanged(lastWeatherLocation: Coord): Boolean {
        return true
    }

    override suspend fun getLocationStr(): String {
        return "Gomel"
    }
}