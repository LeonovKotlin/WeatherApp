package com.example.weatherapp.db.unitlocalized.future

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName
import org.threeten.bp.LocalDate

data class MetricFutureWeather(
        @ColumnInfo(name = "dtTxt")
        override val dtTxt: String,
        @ColumnInfo(name = "main_temp")
        override val temp: Double,
        @ColumnInfo(name = "main_humidity")
        override val humidity: Int,
        @ColumnInfo(name = "main_pressure")
        override val pressure: Int,
        @ColumnInfo(name = "pop")
        override val pop: Double
) : SpeceficFutureWeather