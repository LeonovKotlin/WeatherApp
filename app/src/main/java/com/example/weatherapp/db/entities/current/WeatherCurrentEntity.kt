package com.example.weatherapp.db.entities.current

import androidx.room.*
import com.google.gson.annotations.SerializedName

const val CURRENT_WEATHER_ID = 0
@Entity(tableName="location_weather")
data class CurrentWeatherResponse(

        @PrimaryKey
        @field:SerializedName("visibility")
        var visibility: Int? = null,

        @SerializedName("timezone")
        var timezone: Int? = null,

        @Embedded
        @SerializedName("main")
        var currentWeather: CurrentWeather? = null,

        @Ignore
        @field:SerializedName("clouds")
        var clouds: Clouds? = null,

        @Embedded
        @field:SerializedName("sys")
        var sys: Sys? = null,

        @SerializedName("dt")
        var dt: Long? = null,

        @Embedded
        @field:SerializedName("coord")
        var coord: Coord? = null,

        @Ignore
        @field:SerializedName("weather")
        var weather: List<WeatherItem?>? = null,

        @field:SerializedName("name")
        var name: String,

        @Ignore
        @field:SerializedName("cod")
        var cod: Int? = null,

//    @field:SerializedName("id")
//    var id: Int? = null,

        @Ignore
        @field:SerializedName("base")
        var base: String? = null,

        @Embedded
        @field:SerializedName("wind")
        var wind: Wind? = null,

        ) {

        val zonedDateTime: org.threeten.bp.ZonedDateTime
                get() {
                        val instant = org.threeten.bp.Instant.ofEpochSecond(dt!!)
                        val zoneid = org.threeten.bp.ZoneId.of(timezone.toString())
                        return org.threeten.bp.ZonedDateTime.ofInstant(instant, zoneid)
                }
        constructor() : this(0, 0, null, null, null, 0, null, null, "", 0, null, null)
}

data class Sys(

        @field:SerializedName("country")
        var country: String? = null,

        @field:SerializedName("sunrise")
        var sunrise: Int? = null,

        @field:SerializedName("sunset")
        var sunset: Int? = null,

        @Ignore
        @field:SerializedName("id")
        var id: Int? = null,

        @field:SerializedName("type")
        var type: Int? = null
)



data class WeatherItem(

        @field:SerializedName("icon")
        var icon: String? = null,

        @field:SerializedName("description")
        var description: String? = null,

        @field:SerializedName("main")
        var main: String? = null,

        @field:SerializedName("id")
        var id: Int? = null
)

data class Wind(

        @field:SerializedName("deg")
        var deg: Int? = null,

        @field:SerializedName("speed")
        var speed: Double? = null
)

data class Clouds(

        @field:SerializedName("all")
        var all: Int? = null
)

{
    @PrimaryKey(autoGenerate = false)
    var id:Int = CURRENT_WEATHER_ID

}




