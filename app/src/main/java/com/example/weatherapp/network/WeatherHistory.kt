package com.example.weatherapp.network


import com.example.weatherapp.network.entities.Current
import com.example.weatherapp.network.entities.Hourly
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