package com.example.weatherapp.db.entities.current


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

const val CURRENT_WEATHER_ID = 0
@Entity(tableName="current_weather")
data class Main(
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("pressure")
    val pressure: Int,
    @SerializedName("temp")
    val temp: Double,
    @SerializedName("tempMax")
    val tempMax: Double,
    @SerializedName("tempMin")
    val tempMin: Double,
)
{
    @PrimaryKey(autoGenerate = false)
    var id:Int = CURRENT_WEATHER_ID
}