package com.example.weatherapp.network


import com.example.weatherapp.db.entities.current.*
import com.google.gson.annotations.SerializedName

data class WeatherCurrentResponse(
    @SerializedName("base")
    val base: String,
//    @SerializedName("main")
//    val weatherCurrentEntity: WeatherCurrentEntity,
    @SerializedName("clouds")
    val clouds: Clouds,
        @SerializedName("cod")
    val cod: Int,
        @SerializedName("coord")
    val coord: Coord,
        @SerializedName("dt")
    val dt: Int,
        @SerializedName("id")
    val id: Int,
        @SerializedName("main")
    val main: Main,
        @SerializedName("name")
    val name: String,
        @SerializedName("sys")
    val sys: Sys,
        @SerializedName("timezone")
    val timezone: Int,
        @SerializedName("visibility")
    val visibility: Int,
        @SerializedName("weather")
    val weather: List<Weather>,
        @SerializedName("wind")
    val wind: Wind
)