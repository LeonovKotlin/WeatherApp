package com.example.weatherapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherapp.db.entities.current.CURRENT_WEATHER_ID
import com.example.weatherapp.db.entities.current.Coord

@Dao
interface WeatherLocDao {
     @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun upsert(weatherLocation: Coord)

     @Query("select * from weather_location where id=$CURRENT_WEATHER_ID")
     fun getLocation() : LiveData<WeatherLocDao>

}