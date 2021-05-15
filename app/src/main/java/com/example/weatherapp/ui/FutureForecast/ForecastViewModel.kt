package com.example.weatherapp.ui.FutureForecast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.internal.Unit
import com.example.weatherapp.internal.lazyDeferred
import com.example.weatherapp.repository.ForecastRepository
import com.example.weatherapp.ui.Today.TodayViewModel
import com.example.weatherapp.ui.base.WeatherViewModel
import org.threeten.bp.LocalDate

class ForecastViewModel(
        private val forecastRepository: ForecastRepository,
) : WeatherViewModel(forecastRepository) {
        private val unit = Unit.METRIC

        val weatherEntries by lazyDeferred {
            forecastRepository.getFutureWeatherList(isMetric)
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