@file:Suppress("AndroidUnresolvedRoomSqlReference")

package com.example.weatherapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherapp.db.entities.future.ForecastWeatherEntry
import com.example.weatherapp.db.unitlocalized.future.ImperialFutureWeather
import com.example.weatherapp.db.unitlocalized.future.MetricFutureWeather

@Dao
interface FutureWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(forecastWeatherEntry: List<ForecastWeatherEntry>)

    @Query("select * from future_weather")
    fun getFutureWeatherMetric() : LiveData<List<MetricFutureWeather>>

    @Query("select * from future_weather")
    fun getFutureWeatherImperial() : LiveData<List<ImperialFutureWeather>>

    @Query("select count(id) from future_weather")
    fun countFutureWeather() : Int

    @Query("delete from future_weather")
    fun deleteOldEntries()
}
//@Query("select * from future_weather where dtTxt(dtTxt) >= data(:startDate)")
//fun getFutureWeatherMetric(startDate: LocalDate) : LiveData<List<MetricFutureWeather>>
//
//@Query("select * from future_weather where dt(dt) >= data(:startDate)")
//fun getFutureWeatherImperial(startDate: LocalDate) : LiveData<List<ImperialFutureWeather>>
//
//@Query("select count(id) from future_weather where dt(dt) >= data(:startDate)")
//fun countFutureWeather(startDate: LocalDate) : Int
//
//@Query("delete from future_weather where dt(dt) < data(:firstDateToKeep)")
//fun deleteOldEntries(firstDateToKeep: LocalDate)
