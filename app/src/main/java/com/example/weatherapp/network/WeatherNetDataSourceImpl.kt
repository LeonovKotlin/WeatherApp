package com.example.weatherapp.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.network.response.CurrentWeatherResponse
import com.example.weatherapp.network.api.APIService
import com.example.weatherapp.network.internal.NoConnectivityExeption
import com.example.weatherapp.network.response.FutureWeatherResponse

class WeatherNetDataSourceImpl(
private val apiService: APIService
) : WeatherNetDataSource {
    private val _downloadedCurrentWeather = MutableLiveData<CurrentWeatherResponse>()
    override val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>
    get() = _downloadedCurrentWeather

    override suspend fun fetchCurrentWeather(location: String) {
        try {
            val fetchedCurrentWeather = apiService
                    .getLastWeekWeather(location)
                    _downloadedCurrentWeather.postValue(fetchedCurrentWeather.body())
        }
        catch (e: NoConnectivityExeption) {
            Log.e("Connect", "No internet", e)
        }
    }
    private val _downloadedFutureWeather = MutableLiveData<FutureWeatherResponse>()
    override val downloadedFutureWeather: LiveData<FutureWeatherResponse>
        get() = _downloadedFutureWeather

    override suspend fun fetchFutureWeather(location: String) {
        try {
            val fetchedFutureWeather = apiService
                    .getFutureWeather(location)
            _downloadedFutureWeather.postValue(fetchedFutureWeather.body())

        }
        catch (e: NoConnectivityExeption) {
            Log.e("Connect", "No internet", e)
        }
    }
}

