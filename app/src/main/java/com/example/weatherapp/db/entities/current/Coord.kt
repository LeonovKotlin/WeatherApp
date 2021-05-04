package com.example.weatherapp.db.entities.current


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

const val WEATHER_LOCATION_ID = 0
@Entity(tableName="weather_location")
data class Coord(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lon")
    val lon: Double
)
{
    @PrimaryKey(autoGenerate = false)
    var id:Int = 0
}