package com.example.weatherapp.db.unitlocalized.future

import androidx.room.ColumnInfo
import org.threeten.bp.LocalDate


interface SpeceficFutureWeather  {
    val dtTxt: String
    val temp: Double
    val humidity: Int
    val pressure: Int
    val pop: Double
}