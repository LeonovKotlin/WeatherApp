package com.example.weatherapp.network.provider

import com.example.weatherapp.db.entities.current.Coord

class LocationPrividerImpl : LocationPrivider {
    override suspend fun locationChanged(lastWeatherLocation: Coord): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun getLocationStr(): String {
        TODO("Not yet implemented")
    }
}