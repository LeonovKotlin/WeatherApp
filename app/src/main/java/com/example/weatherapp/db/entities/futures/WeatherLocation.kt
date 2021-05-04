package com.example.weatherapp.db.entities.futures


import com.google.gson.annotations.SerializedName

data class WeatherLocation(
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