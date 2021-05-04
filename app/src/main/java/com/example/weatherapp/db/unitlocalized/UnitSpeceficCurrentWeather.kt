package com.example.weatherapp.db.unitlocalized

interface UnitSpeceficCurrentWeather  {
    val temp: Double
    val visibility: Int
    val sunrise: Int
    val sunset: Int
    val humidity: Int
    val pressure: Int
    val tempMax: Double
    val tempMin: Double
    val windspeed: Int
}