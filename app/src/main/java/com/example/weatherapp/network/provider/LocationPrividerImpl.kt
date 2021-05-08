package com.example.weatherapp.network.provider

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.content.ContextCompat
import com.example.weatherapp.db.entities.current.CurrentWeatherResponse
import com.example.weatherapp.db.entities.current.WeatherLocation
import com.example.weatherapp.db.unitlocalized.location.CurrentWeatherLocation
import com.example.weatherapp.internal.asDeferred
import com.example.weatherapp.network.internal.LocationPermissionNotException
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.Deferred
import retrofit2.Response
import java.util.jar.Manifest

const val USE_DEVICE_LOCATION = "USE_DEVICE_LOCATION"
const val CUSTOM_LOCATION = "CUSTOM_LOCATION"

class LocationPrividerImpl(
        private val fusedLocationProviderClient: FusedLocationProviderClient,
        context: Context) : PreferenceProvider(context), LocationPrivider {
    private val appContext = context.applicationContext

    override suspend fun haslocationChanged(lastWeatherLocation: WeatherLocation): Boolean {
        val deviceLocationChanged = try {
            hasDevicelocationChanged(lastWeatherLocation)
        } catch (e: LocationPermissionNotException) {
            false
        }
        return deviceLocationChanged || hascustomLocChanged(lastWeatherLocation)
    }
  override suspend fun getPreferredLocationString() :  String {
      if (isUsingDeviseLocation()) {
          try {
              val deviceLocation = getLastDeviceLocation().await()
                      ?: return "${getCustomLocationName()}"
              return "${deviceLocation.longitude},${deviceLocation.longitude}"
          } catch (e: LocationPermissionNotException) {
              return "${getCustomLocationName()}"
          }
      }
      else
          return "${getCustomLocationName()}"
  }

    private suspend fun hasDevicelocationChanged(lastWeatherLocation: WeatherLocation) : Boolean {
        if (!isUsingDeviseLocation())
            return false
        val deviceLocation = getLastDeviceLocation().await()
                ?: return false

        val comp = 0.03
        return  Math.abs(deviceLocation.latitude - lastWeatherLocation.lat) > comp &&
                Math.abs(deviceLocation.longitude- lastWeatherLocation.lon) > comp
    }
    private fun hascustomLocChanged(lastWeatherLocation: WeatherLocation) : Boolean {
        val customLocationName = getCustomLocationName()
        return customLocationName != lastWeatherLocation.lat.toString()   //loc.name(lat,lon)
    }
//    private fun hascustomLocChanged(lastWeatherLocation: WeatherLocation) : Boolean {
//        val customLocationName = getCustomLocationName()
//        return customLocationName != lastWeatherLocation   //loc.name(lat,lon)
//    }
    private fun isUsingDeviseLocation() : Boolean {
        return preferences.getBoolean(USE_DEVICE_LOCATION, true)
    }
//    private fun getCustomLocationName() : Boolean {
//        return preferences.getBoolean(CUSTOM_LOCATION, true)
//    }
private fun getCustomLocationName() : String? {
        return preferences.getString(CUSTOM_LOCATION,null)
    }
    @SuppressLint("MissingPermission")
    private fun getLastDeviceLocation() : Deferred<Location?> {
return if(hasLocPermission())
    fusedLocationProviderClient.lastLocation.asDeferred()
                else
                    throw LocationPermissionNotException()
    }
    private fun hasLocPermission() : Boolean {
            return ContextCompat.checkSelfPermission(appContext,
                   android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED

        }
    }
