package com.example.weatherapp.db.entities


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

const val CURRENT_WEATHER_ID = 0
@Entity(tableName="current_weather")
data class Current(
    @SerializedName("dt")
    val dt: Int,
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("pressure")
    val pressure: Int,
    @SerializedName("sunrise")
    val sunrise: Int,
    @SerializedName("sunset")
    val sunset: Int,
    @SerializedName("temp")
    val temp: Double,
//    @Embedded(prefix = "weather_")
//    val weather: List<Weather>,
//    @SerializedName("wind_speed")
//    val windSpeed: Int
)
{
    @PrimaryKey(autoGenerate = false)
    var id:Int = CURRENT_WEATHER_ID
}