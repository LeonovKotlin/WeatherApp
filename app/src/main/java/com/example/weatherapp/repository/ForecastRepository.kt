package com.example.weatherapp.repository

import androidx.lifecycle.LiveData
import com.example.weatherapp.db.entities.current.CurrentWeatherResponse
import com.example.weatherapp.db.unitlocalized.UnitSpeceficCurrentWeather
import com.example.weatherapp.db.unitlocalized.future.SpeceficFutureWeather

interface ForecastRepository {
suspend fun getCurrentWeather() : LiveData<out UnitSpeceficCurrentWeather>
suspend fun getFutureWeatherList(
) : LiveData<out List<SpeceficFutureWeather>>
suspend fun getWeatherLocation() : LiveData<CurrentWeatherResponse>
}
