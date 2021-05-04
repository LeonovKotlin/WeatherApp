package com.example.weatherapp.db.unitlocalized

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class ImperialCurrentWeather(
        @ColumnInfo(name = "id")
        override val id: Int,
        @ColumnInfo(name = "humidity")
        override val humidity: Int,
        @ColumnInfo(name = "pressure")
        override val pressure: Int,
        @ColumnInfo(name = "temp")
        override val temp: Double,
        @ColumnInfo(name = "tempMax")
        override val tempMax: Double,
        @ColumnInfo(name = "tempMin")
        override val tempMin: Double



) : UnitSpeceficCurrentWeather

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
