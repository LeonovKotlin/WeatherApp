package com.example.weatherapp.network

import android.content.Context
import android.net.ConnectivityManager
import com.example.weatherapp.network.internal.NoConnectivityExeption
import okhttp3.Interceptor
import okhttp3.Response

class ConnectInterceptorImpl(context: Context) : ConnectInterceptor {
    private val appContext = context.applicationContext
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isOnline())
            throw NoConnectivityExeption()
        return chain.proceed(chain.request())
    }
        private fun isOnline(): Boolean {
        val connectivityManager = appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo !=null && networkInfo.isConnected
    }
}