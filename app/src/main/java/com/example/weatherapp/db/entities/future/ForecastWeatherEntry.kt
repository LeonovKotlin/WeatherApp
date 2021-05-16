package com.example.weatherapp.db.entities.future


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "future_weather", indices = [Index(value = ["dtTxt"], unique = true)])
data class ForecastWeatherEntry(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @field:SerializedName("dt")
    val dt: Int? = null,
    @field:SerializedName("dt_txt")
    val dtTxt: String? = null,
    @Embedded(prefix = "main_")
    val main: Main? = null,
//    @SerializedName("pop")
//    val pop: Double,
//    @SerializedName("visibility")
//    val visibility: Int,
//    @SerializedName("weather")
//    val weather: List<Weather>,
//    @SerializedName("wind")
//    val wind: Wind
)