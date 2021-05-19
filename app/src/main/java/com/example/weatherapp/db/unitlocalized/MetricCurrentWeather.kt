package com.example.weatherapp.db.unitlocalized

import androidx.room.ColumnInfo

class MetricCurrentWeather(
        @ColumnInfo(name = "humidity")
        override val humidity: Int,
        @ColumnInfo(name = "pressure")
        override val pressure: Int,
        @ColumnInfo(name = "temp")
        override val temp: Double,
        @ColumnInfo(name = "tempMax")
        override val tempMax: Double,
        @ColumnInfo(name = "tempMin")
        override val tempMin: Double,
) : UnitSpeceficCurrentWeather
