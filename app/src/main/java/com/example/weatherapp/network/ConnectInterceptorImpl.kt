package com.example.weatherapp.network

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.core.content.ContextCompat.getSystemService
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
//    private fun isOnline(cm: ConnectivityManager, network: Network?): Boolean {
//        cm.getNetworkCapabilities(network)?.also {
//            if (it.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
//                return true
//            } else if (it.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
//                return true
//            }
//        }
//        return false
//    }
//}
//    private fun isOnline(): Boolean {
//        val connectivityManager =
//                appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        val capabilities =
//                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
//        if (capabilities != null) {
//            when {
//                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
//                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
//                    return true
//                }
//                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
//                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
//                    return true
//                }
//                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
//                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
//                    return true
//                }
//            }
//        }
//        return false
//    }
//}




//private fun isOnline(): Boolean {
//    var result = false
//    val connectivityManager =appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//        val networkCapabilities = connectivityManager.activeNetwork ?: return false
//        val actNw =
//                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
//        result = when {
//            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
//            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
//            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
//            else -> false
//        }
//    } else {
//        connectivityManager.run {
//            connectivityManager.activeNetworkInfo?.run {
//                result = when (type) {
//                    ConnectivityManager.TYPE_WIFI -> true
//                    ConnectivityManager.TYPE_MOBILE -> true
//                    ConnectivityManager.TYPE_ETHERNET -> true
//                    else -> false
//                }
//
//            }
//        }
//    }
//
//    return result





    //    private fun isOnline() : Boolean {
//        val connectivityManager = appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        val networkInfo = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
//        capabilities.also {
//            if (it != null){
//                if (it.hasTransport(NetworkCapabilities.TRANSPORT_WIFI))
//                    return true
//                else if (it.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)){
//                    return true
//                }
//            }
//        }
//        return false
//    }
//    private fun isOnline(): Boolean {
//        val connectivityManager = appContext.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
//        return if (Build.VERSION.SDK_INT < 23) {
//            val activeNetworkInfo = connectivityManager.activeNetworkInfo
//            activeNetworkInfo != null && activeNetworkInfo.isConnected
//        } else {
//            val nc = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
//            if (nc == null) {
//                false
//            } else {
//                nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
//                        nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
//            }
//        }
//    }
//}



//