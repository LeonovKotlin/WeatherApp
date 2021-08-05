package com.example.weatherapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherapp.network.response.CURRENT_WEATHER_ID
import com.example.weatherapp.network.response.CurrentWeatherResponse

@Dao
interface WeatherLocDao {
     @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun upsert(weatherLocation: CurrentWeatherResponse)

     @Query("select * from location_weather where id=$CURRENT_WEATHER_ID")
     fun getLocation() : LiveData<CurrentWeatherResponse>

     @Query("select * from location_weather where id=$CURRENT_WEATHER_ID")
     fun getLocationNonLive() : CurrentWeatherResponse?
}