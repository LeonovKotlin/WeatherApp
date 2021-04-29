package com.example.weatherapp.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("onecall/timemachine")
    suspend fun getLastWeekWeather(
        @Query("lat") lat: Double = 53.9,
        @Query("lon") lon: Double = 27.56,
        @Query("dt") period: Long = 1619481600,
        @Query("appid") apiKey: String = Constants.openWeatherApiKey
    ): Response<WeatherHistory>

}