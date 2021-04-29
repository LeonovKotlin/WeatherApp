package com.example.weatherapp.ui.Today

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.weatherapp.R
import com.example.weatherapp.network.RetrofitInstance
import retrofit2.HttpException
import java.io.IOException
const val TAG = "MainActivity"
class TodayFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.today_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getHistoricalWeather()

    }
    private fun getHistoricalWeather() {
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