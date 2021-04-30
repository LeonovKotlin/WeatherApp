package com.example.weatherapp.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.network.internal.NoConnectivityExeption
import okhttp3.Response
import java.nio.channels.NotYetConnectedException

class WeatherNetDataSourceImpl(
        private val apiService: APIService
) : WeatherNetDataSource {
    private val _downloadedCurrentWeather = MutableLiveData<WeatherHistory>()
    override val downloadedCurrentWeather: LiveData<WeatherHistory>
        get() = _downloadedCurrentWeather

    override suspend fun fetchCurrentWeather(lat: Double, lon: Double, period: Long) {
        try {
            val fetchedCurrentWeather = apiService
                    .getLastWeekWeather(lat,lon,period)
                    .await()
                    _downloadedCurrentWeather.postValue(fetchedCurrentWeather)

        }
        catch (e: NoConnectivityExeption) {
            Log.e("Connect", "No internet", e)
        }
    }
}

