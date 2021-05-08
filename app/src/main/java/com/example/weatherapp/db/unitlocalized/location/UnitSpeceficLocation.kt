package com.example.weatherapp.db.unitlocalized.location

import com.google.gson.annotations.SerializedName

interface UnitSpeceficLocation  {
    var country: String
    var timezone: Int
    var name: String
    var lat: Double
    var lon: Double

}