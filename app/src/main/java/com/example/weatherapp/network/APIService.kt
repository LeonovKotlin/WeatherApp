package com.example.weatherapp.network

import com.example.weatherapp.db.entities.Current
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("onecall/timemachine")
    suspend fun getLastWeekWeather(
        @Query("lat") lat: Double = 53.9,
        @Query("lon") lon: Double = 27.56,
        @Query("dt") period: Long = 1619481600,
//        @Query("appid") apiKey: String = Constants.openWeatherApiKey
    ): Response<Current>
//    @Query("lat") lat: Double = 53.9,
//    @Query("lon") lon: Double = 27.56,
//    @Query("dt") period: Long = 1619481600,
//    @Query("appid") apiKey: String = Constants.openWeatherApiKey
    companion object  {
        operator fun invoke() : APIService {

            val reqestInterceptor = Interceptor { chain->
                val url = chain.request()
                    .url
                    .newBuilder()
                    .addQueryParameter("appid",Constants.openWeatherApiKey)
                    .build()
                val reqest = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()
                return@Interceptor chain.proceed(reqest)
            }
                    val okHttpClient = OkHttpClient.Builder()
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
