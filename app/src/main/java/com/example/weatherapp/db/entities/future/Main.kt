package com.example.weatherapp.db.entities.future


import com.google.gson.annotations.SerializedName

data class Main(
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("pressure")
    val pressure: Int,
    @SerializedName("temp")
    val temp: Double,
)