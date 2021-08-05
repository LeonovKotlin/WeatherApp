package com.example.weatherapp.ui.FutureForecast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.internal.lazyDeferred
import com.example.weatherapp.repository.ForecastRepository
import com.example.weatherapp.ui.base.WeatherViewModel

class ForecastViewModel(
        private val forecastRepository: ForecastRepository,
) : WeatherViewModel(forecastRepository) {

        val weatherEntries by lazyDeferred {
            forecastRepository.getFutureWeatherList()
    }
    }
class  FutureViewModelFactory(
        private val forecastRepository: ForecastRepository
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ForecastViewModel(forecastRepository) as T
    }
}