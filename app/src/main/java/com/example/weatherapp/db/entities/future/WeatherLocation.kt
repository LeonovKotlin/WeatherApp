package com.example.weatherapp.db.entities.future


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

const val WEATHER_LOCATION_ID = 0
@Entity(tableName = "weather_location")
data class WeatherLocation(
    @SerializedName("coord")
    val coord: Coord,
    @SerializedName("country")
    val country: String,
//    @SerializedName("id")
//    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("population")
    val population: Int,
    @SerializedName("sunrise")
    val sunrise: Int,
    @SerializedName("sunset")
    val sunset: Int,
    @SerializedName("timezone")
    val timezone: Int
)
{
    @PrimaryKey(autoGenerate = false)
    var id: Int = WEATHER_LOCATION_ID
}