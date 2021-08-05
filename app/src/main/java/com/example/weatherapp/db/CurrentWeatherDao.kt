package com.example.weatherapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherapp.db.entities.current.CURRENT_WEATHER_ID2
import com.example.weatherapp.db.entities.current.CurrentWeather
import com.example.weatherapp.db.unitlocalized.current.MetricCurrentWeather

@Dao
interface CurrentWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(weather: CurrentWeather)

    @Query("select * from current_weather where id=$CURRENT_WEATHER_ID2")
    fun getWeatherMetric() : LiveData<MetricCurrentWeather>

}
