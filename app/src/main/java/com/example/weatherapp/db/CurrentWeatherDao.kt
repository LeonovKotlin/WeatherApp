package com.example.weatherapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherapp.db.entities.CURRENT_WEATHER_ID
import com.example.weatherapp.db.entities.Current
import com.example.weatherapp.db.unitlocalized.ImperialCurrentWeather
import com.example.weatherapp.db.unitlocalized.MetricCurrentWeather

@Dao
interface CurrentWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(weather: Current)

    @Query("select * from current_weather where id=$CURRENT_WEATHER_ID")
    fun getWeatherMetric() : LiveData<MetricCurrentWeather>

    @Query("select * from current_weather where id=$CURRENT_WEATHER_ID")
    fun getWeatherImperial() : LiveData<ImperialCurrentWeather>
}
