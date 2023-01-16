package com.example.mobiletracker

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.provider.Settings
import android.util.Log
import com.google.android.gms.location.*
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class LocationProvider @Inject constructor(@ApplicationContext private val context: Context) {

    private  var fusedLocationClient = LocationServices.getFusedLocationProviderClient(context!!)

    private lateinit var locationRequest: LocationRequest

    private lateinit var locationCallback: LocationCallback
    val id: String = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)

    fun getLocation(action: (MTdatas) -> Unit) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context!!)
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)
                if (locationResult.locations.isNotEmpty()) {
                    val location = locationResult.lastLocation
                    val mTdatas = MTdatas(
                        serialNumber = id,
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
    private fun startLocationUpdates() {
        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            null
        )
    }

    // stop location updates
    private fun stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    /*// stop receiving location update when activity not visible/foreground
    override fun onPause() {
        super.onPause()
        stopLocationUpdates()
    }*/

    /*// start receiving location update when activity  visible/foreground
    override fun onResume() {
        super.onResume()
        startLocationUpdates()
    }*/

}
