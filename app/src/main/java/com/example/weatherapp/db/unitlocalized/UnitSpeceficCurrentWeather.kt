package com.example.weatherapp.db.unitlocalized

import com.google.gson.annotations.SerializedName

interface UnitSpeceficCurrentWeather  {
    val temp: Double
    val humidity: Int
    val pressure: Int
    val tempMax: Double
    val tempMin: Double
}