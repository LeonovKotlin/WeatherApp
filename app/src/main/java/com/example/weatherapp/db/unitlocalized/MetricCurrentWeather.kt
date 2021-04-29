package com.example.weatherapp.db.unitlocalized

import androidx.room.ColumnInfo

class MetricCurrentWeather(
@ColumnInfo(name="temp")
override val temp: Double,
@ColumnInfo(name="dt")
override val dt: Int,
@ColumnInfo(name="humidity")
override val humidity: Int,
@ColumnInfo(name="pressure")
override val pressure: Int,
@ColumnInfo(name="sunrise")
override val sunrise: Int,
@ColumnInfo(name="sunset")
override val sunset: Int
//@ColumnInfo(name="wind_speed")
//override val windSpeed: Int,
//@ColumnInfo(name="weather_description")
//override val description: String,
//@ColumnInfo(name="weather_main")
//override val main: String,
//@ColumnInfo(name="weather_icon")
//override val icon: String
) : UnitSpeceficCurrentWeather