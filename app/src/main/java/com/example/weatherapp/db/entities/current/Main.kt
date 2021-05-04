package com.example.weatherapp.db.entities.current


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

const val CURRENT_WEATHER_ID = 0
@Entity(tableName="current_weather")
data class Main(
//    @SerializedName("feels_like")
//    val feelsLike: Double,
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("pressure")
    val pressure: Int,
    @SerializedName("temp")
    val temp: Double,
    @SerializedName("temp_max")
    val tempMax: Double,
    @SerializedName("temp_min")
    val tempMin: Double
)
{
    @PrimaryKey(autoGenerate = true)
    var id:Int = CURRENT_WEATHER_ID
}