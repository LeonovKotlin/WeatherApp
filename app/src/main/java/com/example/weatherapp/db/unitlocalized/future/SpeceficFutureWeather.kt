package com.example.weatherapp.db.unitlocalized.future

import com.google.gson.annotations.SerializedName
import org.threeten.bp.LocalDate

interface SpeceficFutureWeather  {
    val dt: LocalDate
    val temp: Double
    val humidity: Int
    val pressure: Int
}