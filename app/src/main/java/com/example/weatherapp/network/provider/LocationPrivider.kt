package com.example.weatherapp.network.provider

import com.example.weatherapp.db.entities.current.Coord
import com.example.weatherapp.db.entities.futures.WeatherLocation

interface LocationPrivider {
    suspend fun locationChanged(lastWeatherLocation: Coord): Boolean
    suspend fun getLocationStr():String
}