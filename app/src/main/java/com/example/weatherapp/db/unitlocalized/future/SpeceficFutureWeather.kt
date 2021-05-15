package com.example.weatherapp.db.unitlocalized.future

import org.threeten.bp.LocalDate


interface SpeceficFutureWeather  {
    val dtTxt: String
    val temp: Double
    val humidity: Int
    val pressure: Int
}