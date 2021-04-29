package com.example.weatherapp.db.unitlocalized

class ImperialCurrentWeather(
        override val dt: Int,
        override val humidity: Int,
        override val pressure: Int,
        override val sunrise: Int,
        override val sunset: Int,
        override val temp: Double,
        override val windSpeed: Int,
        override val description: String,
        override val main: String
) : UnitSpeceficCurrentWeather
