package com.example.weatherapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.weatherapp.db.entities.current.CurrentWeather
import com.example.weatherapp.db.entities.current.CurrentWeatherResponse
import com.example.weatherapp.db.entities.future.ForecastWeatherEntry

@Database(
        entities = [CurrentWeather::class, ForecastWeatherEntry::class, CurrentWeatherResponse::class],
        version = 1,
        exportSchema = false
)
//@TypeConverters(LocalDateConverter::class)
abstract class ForecastDatabase : RoomDatabase() {
abstract fun currentWeatherDao() : CurrentWeatherDao
abstract fun futureWeatherDao() : FutureWeatherDao
abstract fun weatherLocDao() : WeatherLocDao


companion object {
  @Volatile private  var instance: ForecastDatabase? = null
  private val LOCK = Any()
    operator fun invoke(context: Context) = instance?: synchronized(LOCK) {
        instance ?: buildDatabase(context).also { instance = it }
    }
  private fun buildDatabase(context: Context) =
    Room.databaseBuilder(context.applicationContext,
    ForecastDatabase::class.java, "forecast.db")
    .build()
}
}


