package com.example.weatherapp.db.entities.futures


import com.example.weatherapp.db.entities.futures.Current
import com.example.weatherapp.db.entities.futures.Hourly
import com.google.gson.annotations.SerializedName

data class WeatherHistory(
        @SerializedName("current")
    val current: Current,
        @SerializedName("hourly")
    val hourly: List<Hourly>,
        @SerializedName("lat")
    val lat: Double,
        @SerializedName("lon")
    val lon: Double,
        @SerializedName("timezone")
    val timezone: String,
        @SerializedName("timezone_offset")
    val timezoneOffset: Int
)