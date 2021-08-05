package com.example.weatherapp.network.provider

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.content.ContextCompat
import com.example.weatherapp.network.response.CurrentWeatherResponse
import com.example.weatherapp.internal.asDeferred
import com.example.weatherapp.network.internal.LocationPermissionNotException
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.Deferred

const val USE_DEVICE_LOCATION = "USE_DEVICE_LOCATION"
const val CUSTOM_LOCATION = "CUSTOM_LOCATION"

class LocationPrividerImpl(
        private val fusedLocationProviderClient: FusedLocationProviderClient,
        context: Context) : PreferenceProvider(context), LocationPrivider {
    private val appContext = context.applicationContext

    override suspend fun haslocationChanged(lastWeatherLocation: CurrentWeatherResponse): Boolean { ///temp
        val deviceLocationChanged = try {
            hasDevicelocationChanged(lastWeatherLocation)
        } catch (e: LocationPermissionNotException) {
            false
        }
        return deviceLocationChanged || hascustomLocChanged(lastWeatherLocation)
    }
  override suspend fun getLocationStr():  String {
      if (isUsingDeviseLocation()) {
          try {
              val deviceLocation = getLastDeviceLocation().await()
                      ?: return "${getCustomLocationName()}"
              return "${deviceLocation.latitude},${deviceLocation.longitude}"
          } catch (e: LocationPermissionNotException) {
              return "${getCustomLocationName()}"
          }
      }
      else
          return "${getCustomLocationName()}"
  }

    private suspend fun hasDevicelocationChanged(lastWeatherLocation: CurrentWeatherResponse) : Boolean {
        if (!isUsingDeviseLocation())
            return false
        val deviceLocation = getLastDeviceLocation().await()
                ?: return false
        val comp = 0.03 //comparisonThreshold
        return  Math.abs(deviceLocation.latitude - lastWeatherLocation.coord!!.lat!!) > comp &&
                Math.abs(deviceLocation.longitude- lastWeatherLocation.coord!!.lon!!) > comp
    }
    private fun hascustomLocChanged(lastWeatherLocation: CurrentWeatherResponse) : Boolean {
        if (!isUsingDeviseLocation()) {
            val customLocationName = getCustomLocationName()
            return customLocationName != lastWeatherLocation.name  //loc.name(lat,lon)
        }
        return false
    }
    private fun isUsingDeviseLocation() : Boolean {
        return preferences.getBoolean(USE_DEVICE_LOCATION, true)
    }
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