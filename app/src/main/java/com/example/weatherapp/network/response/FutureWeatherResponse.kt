package com.example.weatherapp.network.response


import com.example.weatherapp.db.entities.future.ForecastWeatherEntry
import com.google.gson.annotations.SerializedName

data class FutureWeatherResponse(
    @field:SerializedName("city")
    val location: CurrentWeatherResponse? = null,
    @field:SerializedName("cnt")
    val cnt: Int? = null,
    @field:SerializedName("cod")
    val cod: String? = null,
    @field:SerializedName("list")
    val entries: List<ForecastWeatherEntry>? = null,
    @field:SerializedName("message")
    val message: Int? = null
)
