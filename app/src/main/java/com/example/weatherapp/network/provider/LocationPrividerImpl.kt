package com.example.weatherapp.network.provider

import com.example.weatherapp.db.entities.futures.WeatherLocation

class LocationPrividerImpl : LocationPrivider {
    override suspend fun LocationChanged(lastWeatherLocation: WeatherLocation): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun getLocationStr(): String {
        TODO("Not yet implemented")
    }
}