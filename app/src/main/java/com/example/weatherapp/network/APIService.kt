package com.example.weatherapp.network

import com.example.weatherapp.db.entities.current.CurrentWeatherResponse
import com.example.weatherapp.db.entities.future.FutureWeatherResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
//api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}
interface APIService {
@GET("weather")
suspend fun getLastWeekWeather(
        @Query("q") location: String = "Gomel"
    ): Response<CurrentWeatherResponse>
//https://api.openweathermap.org/data/2.5/forecast?q=Gomel&appid=e7fd5f2e6627b47267f04cba5d03cb5a
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
                    .addQueryParameter("appid",Constants.openWeatherApiKey)
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