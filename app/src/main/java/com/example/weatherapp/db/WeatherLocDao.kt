package com.example.weatherapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherapp.db.entities.future.WEATHER_LOCATION_ID
import com.example.weatherapp.db.entities.future.WeatherLocation

@Dao
interface WeatherLocDao {
     @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun upsert(weatherLocation: WeatherLocation)

     @Query("select * from weather_location where id=$WEATHER_LOCATION_ID")
     fun getLocation() : LiveData<WeatherLocation>

}