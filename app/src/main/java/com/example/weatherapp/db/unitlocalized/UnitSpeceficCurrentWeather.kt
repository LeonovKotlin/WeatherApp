package com.example.weatherapp.db.unitlocalized

interface UnitSpeceficCurrentWeather {
    val temp: Double
    val sunrise: Long
    val sunset: Long
    val tempMin: Double
    val tempMax: Double
    val windSpeed: Int
}