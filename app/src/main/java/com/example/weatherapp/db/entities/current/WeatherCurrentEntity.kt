package com.example.weatherapp.db.entities.current


import androidx.room.Embedded
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

const val CURRENT_WEATHER_ID = 0
@Entity(tableName="current_weather")
data class WeatherCurrentEntity(

    @Embedded(prefix="main_")
    val main: Main,
    @Embedded(prefix="sys_")
    val sys: Sys,
    @SerializedName("visibility")
    val visibility: Int,
    @Embedded(prefix="wind_")
    val wind: Wind
)
//    @SerializedName("weather")
//    val weather: List<Weather>,