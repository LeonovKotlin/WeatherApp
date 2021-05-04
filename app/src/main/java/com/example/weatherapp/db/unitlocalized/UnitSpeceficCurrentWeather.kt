package com.example.weatherapp.db.unitlocalized

interface UnitSpeceficCurrentWeather  {
    val id:Int
    val temp: Double
    val humidity: Int
    val pressure: Int
    val tempMax: Double
    val tempMin: Double
}