package com.example.weatherapp.db.unitlocalized.location

import androidx.room.ColumnInfo

data class CurrentWeatherLocation(
        @ColumnInfo(name = "country")
        override var country: String,
        @ColumnInfo(name = "timezone")
        override var timezone: Int,
        @ColumnInfo(name = "name")
        override var name: String,
        @ColumnInfo(name = "lat")
        override var lat: Double,
        @ColumnInfo(name = "lon")
        override var lon: Double
) : UnitSpeceficLocation

