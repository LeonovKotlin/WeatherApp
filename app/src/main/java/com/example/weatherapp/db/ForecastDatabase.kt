package com.example.weatherapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.weatherapp.db.entities.current.Coord
import com.example.weatherapp.db.entities.current.Main

@Database(
        entities = [Main::class, Coord::class],
        version = 1
//    , exportSchema = false
)
abstract class ForecastDatabase : RoomDatabase() {
abstract fun currentWeatherDao() : CurrentWeatherDao
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


