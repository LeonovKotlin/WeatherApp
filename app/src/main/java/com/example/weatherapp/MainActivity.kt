package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.weatherapp.network.RetrofitInstance
import retrofit2.HttpException
import java.io.IOException

const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getHistoricalWeather()
    }
private fun getHistoricalWeather(){
    lifecycleScope.launchWhenCreated {
        val response = try {
            RetrofitInstance.aPIService.getLastWeekWeather()
        } catch (e: IOException) {
            Log.e(TAG, "you might not have internet connection")
            return@launchWhenCreated
        } catch (e: HttpException) {
            Log.e(TAG, "you might not have internet connection")
            return@launchWhenCreated
        }
        if (response.isSuccessful && response.body() != null) {
            val historicalWeather = response.body()
            Log.d(TAG, "WeatherHistory: $historicalWeather")
        } else {
            Log.e(TAG, "Request failed. Code: ${response.code()}, Error: ${response.message()}")
        }
    }
}



}