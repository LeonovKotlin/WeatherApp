package com.example.weatherapp.db.entities.future


import com.google.gson.annotations.SerializedName

data class FutureWeatherResponse(
    @SerializedName("city")
    val location: WeatherLocation,
    @SerializedName("cnt")
    val cnt: Int,
    @SerializedName("cod")
    val cod: String,
    @SerializedName("list")
    val entries: List<ForecastWeatherEntry>,
    @SerializedName("message")
    val message: Int
)