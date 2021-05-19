package com.example.weatherapp.ui.base

import androidx.lifecycle.ViewModel
import com.example.weatherapp.internal.Unit
import com.example.weatherapp.internal.lazyDeferred
import com.example.weatherapp.repository.ForecastRepository

abstract class WeatherViewModel(
        private val forecastRepository: ForecastRepository,
) : ViewModel() {

    val weatherLocation by lazyDeferred {
        forecastRepository.getWeatherLocation()
    }
}