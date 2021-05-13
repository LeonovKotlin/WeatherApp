package com.example.weatherapp.repository

import androidx.lifecycle.LiveData
import com.example.weatherapp.db.entities.current.CurrentWeatherResponse
import com.example.weatherapp.db.entities.future.WeatherLocation

import com.example.weatherapp.db.unitlocalized.UnitSpeceficCurrentWeather
import com.example.weatherapp.db.unitlocalized.future.SpeceficFutureWeather
import org.threeten.bp.LocalDate

interface ForecastRepository {
suspend fun getCurrentWeather(metric: Boolean) : LiveData<out UnitSpeceficCurrentWeather>
suspend fun getFutureWeatherList(startDate: LocalDate, metric: Boolean
) : LiveData<out List<SpeceficFutureWeather>>
suspend fun getWeatherLocation() : LiveData<WeatherLocation>

}
