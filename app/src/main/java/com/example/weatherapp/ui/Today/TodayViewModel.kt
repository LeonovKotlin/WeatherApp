package com.example.weatherapp.ui.Today

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.internal.Unit
import com.example.weatherapp.internal.lazyDeferred
import com.example.weatherapp.repository.ForecastRepository

class TodayViewModel(
    private val forecastRepository: ForecastRepository
) : ViewModel() {
    private val unit = Unit.METRIC

    val isMetric: Boolean
    get() = unit==Unit.METRIC

    val weather by lazyDeferred {
        forecastRepository.getCurrentWeather(isMetric)}
    val weatherLocation by lazyDeferred {
        forecastRepository.getWeatherLocation()}
}

class  TodayViewModelFactory(
    private val forecastRepository: ForecastRepository
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TodayViewModel(forecastRepository) as T
    }
}