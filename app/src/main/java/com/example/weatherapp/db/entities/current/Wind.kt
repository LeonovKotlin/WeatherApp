package com.example.weatherapp.db.entities.current


import com.google.gson.annotations.SerializedName

data class Wind(
    @SerializedName("deg")
    val deg: Int,
    @SerializedName("gust")
    val gust: Int,
    @SerializedName("speed")
    val speed: Int
)