package com.example.weatherapp.db.entities.current

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
const val CURRENT_WEATHER_ID2 = 0
@Entity(tableName="current_weather")
data class CurrentWeather(

        @field:SerializedName("temp")
        var temp: Double,

        @field:SerializedName("temp_min")
        var tempMin: Double,

        @field:SerializedName("humidity")
        var humidity: Int,

        @field:SerializedName("pressure")
        var pressure: Int,

        @field:SerializedName("feels_like")
        var feelsLike: Double,

        @field:SerializedName("temp_max")
        var tempMax: Double
)
{
    @PrimaryKey(autoGenerate = false)
    var id:Int = CURRENT_WEATHER_ID2
}