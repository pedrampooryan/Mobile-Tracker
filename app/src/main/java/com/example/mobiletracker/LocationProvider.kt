package com.example.mobiletracker

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import com.google.android.gms.location.*
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class LocationProvider @Inject constructor(@ApplicationContext private val context: Context) {

    private  var fusedLocationClient = LocationServices.getFusedLocationProviderClient(context!!)
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback
    @SuppressLint("HardwareIds")
    val id: String = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)

    fun getLocation(action: (MTdatas) -> Unit) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)
                if (locationResult.locations.isNotEmpty()) {
                    val location = locationResult.lastLocation
                    val mTdatas = MTdatas(
                        deviceID = id,
                        latitude = location!!.latitude,
                        longitude = location.longitude
                    )
                    action(mTdatas)
                }
            }
        }
        locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY,5000L)
            .build()
        startLocationUpdates()
    }

    @SuppressLint("MissingPermission")
    fun startLocationUpdates() {
            fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            null
        )
    }

    fun stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }
}
