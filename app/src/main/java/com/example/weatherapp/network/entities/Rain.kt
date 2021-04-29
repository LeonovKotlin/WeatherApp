package com.example.weatherapp.network.entities


import com.google.gson.annotations.SerializedName

data class Rain(
    @SerializedName("1h")
    val h: Double
)