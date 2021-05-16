package com.example.weatherapp.db.entities.future

import com.google.gson.annotations.SerializedName

data class Coord(
    @field:SerializedName("lat")
    val lat: Double? = null,
    @field:SerializedName("lon")
    val lon: Double? = null
)
