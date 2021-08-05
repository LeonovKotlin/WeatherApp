package com.example.weatherapp.network.api

import com.example.weatherapp.network.response.CurrentWeatherResponse
import com.example.weatherapp.network.ConnectInterceptor
import com.example.weatherapp.network.Constants
import com.example.weatherapp.network.response.FutureWeatherResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

@GET("weather")
suspend fun getLastWeekWeather(
        @Query("q") location: String = "Gomel"
    ): Response<CurrentWeatherResponse>

@GET("forecast")
suspend fun getFutureWeather(
        @Query("q") location: String = "Gomel"
): Response<FutureWeatherResponse>

    companion object  {
        operator fun invoke(
        connectInterceptor: ConnectInterceptor
        ) : APIService {

            val reqestInterceptor = Interceptor { chain->
                val url = chain.request()
                    .url
                    .newBuilder()
                    .addQueryParameter("appid", Constants.openWeatherApiKey)
                    .addQueryParameter("units", "metric")
                    .build()
                val reqest = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()
                return@Interceptor chain.proceed(reqest)
            }
                    val okHttpClient = OkHttpClient.Builder()
                        .addInterceptor(connectInterceptor)
                        .addInterceptor(reqestInterceptor)
                        .build()
                        return Retrofit.Builder()

                            .client(okHttpClient)
                            .baseUrl(Constants.openWeatherBaseURL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .build()
                            .create(APIService::class.java)
            }
        }
    }