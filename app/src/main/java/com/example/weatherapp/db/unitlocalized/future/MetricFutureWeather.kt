package com.example.weatherapp.db.unitlocalized.future

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName
import org.threeten.bp.LocalDate

data class MetricFutureWeather(
        @ColumnInfo(name = "dt_txt")
        override val dt: LocalDate,
        @ColumnInfo(name = "main_temp")
        override val temp: Double,
        @ColumnInfo(name = "main_humidity")
        override val humidity: Int,
        @ColumnInfo(name = "main_pressure")
        override val pressure: Int
) : SpeceficFutureWeather

//@ColumnInfo(name = "main_temp")
//override val temp: Double,
//@ColumnInfo(name = "visibility")
//override val visibility: Int,
//@ColumnInfo(name = "sys_sunrise")
//override val sunrise: Int,
//@ColumnInfo(name = "sys_sunset")
//override val sunset: Int,
//@ColumnInfo(name = "main_humidity")
//override val humidity: Int,
//@ColumnInfo(name = "main_pressure")
//override val pressure: Int,
//@ColumnInfo(name = "main_tempMax")
//override val tempMax: Double,
//@ColumnInfo(name = "main_tempMin")
//override val tempMin: Double,
//@ColumnInfo(name = "wind_speed")
//override val windspeed: Int
