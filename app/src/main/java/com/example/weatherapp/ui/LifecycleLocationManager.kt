package com.example.weatherapp.ui

import android.annotation.SuppressLint
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest

class LifecycleLocationManager(
        lifecycleOwner: LifecycleOwner,
        private val fusedLocationProviderClient: FusedLocationProviderClient,
        private val locationCallback: LocationCallback
) : LifecycleObserver {
    init {
        lifecycleOwner.lifecycle.addObserver(this)
    }
    private val locationRequest = LocationRequest().apply {
        interval = 5000
        fastestInterval = 5000
        priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
    }


    @SuppressLint("MissingPermission")
    fun startLocationUpdates() {
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null)
    }
    fun removeLocationUpdates() {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback)
    }
}