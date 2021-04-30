package com.example.weatherapp.db.entities


import androidx.room.Entity
import com.google.gson.annotations.SerializedName
@Entity(tableName="weather")
data class Weather(
    @SerializedName("description")
    val description: String,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("main")
    val main: String
)