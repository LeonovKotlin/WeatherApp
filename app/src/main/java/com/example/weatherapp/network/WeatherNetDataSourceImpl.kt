package com.example.weatherapp.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.network.internal.NoConnectivityExeption

class WeatherNetDataSourceImpl(
private val apiService: APIService
) : WeatherNetDataSource {
    private val _downloadedCurrentWeather = MutableLiveData<WeatherCurrentResponse>()
    override val downloadedCurrentWeather: LiveData<WeatherCurrentResponse>
        get() = _downloadedCurrentWeather

    override suspend fun fetchCurrentWeather(location: String) {
        try {
            val fetchedCurrentWeather = apiService
                    .getLastWeekWeather(location)
                    .await()
                    _downloadedCurrentWeather.postValue(fetchedCurrentWeather)

        }
        catch (e: NoConnectivityExeption) {
            Log.e("Connect", "No internet", e)
        }
    }
}

