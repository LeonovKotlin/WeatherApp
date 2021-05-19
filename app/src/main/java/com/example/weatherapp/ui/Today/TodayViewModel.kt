package com.example.weatherapp.ui.Today

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.internal.Unit
import com.example.weatherapp.internal.lazyDeferred
import com.example.weatherapp.repository.ForecastRepository
import com.example.weatherapp.ui.base.WeatherViewModel

class TodayViewModel(
    private val forecastRepository: ForecastRepository
) : WeatherViewModel(forecastRepository) {
    private val unit = Unit.METRIC

    val weather by lazyDeferred {
        forecastRepository.getCurrentWeather(isMetric)}
}
class  TodayViewModelFactory(
    private val forecastRepository: ForecastRepository
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TodayViewModel(forecastRepository) as T
    }
}