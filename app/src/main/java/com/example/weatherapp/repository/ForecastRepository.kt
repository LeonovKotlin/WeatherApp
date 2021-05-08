package com.example.weatherapp.repository

import androidx.lifecycle.LiveData
import com.example.weatherapp.db.entities.current.WeatherLocation

import com.example.weatherapp.db.unitlocalized.UnitSpeceficCurrentWeather

interface ForecastRepository {
suspend fun getCurrentWeather(metric: Boolean) : LiveData<out UnitSpeceficCurrentWeather>
suspend fun getWeatherLocation() : LiveData<WeatherLocation>

}
