package com.example.mobiletracker

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.google.android.gms.location.*
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class LocationProvider @Inject constructor(@ApplicationContext private val context: Context) {

    private  var fusedLocationClient = LocationServices.getFusedLocationProviderClient(context!!)

    private lateinit var locationRequest: LocationRequest

    private lateinit var locationCallback: LocationCallback

    fun getLocation(action: (MTdatas) -> Unit) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context!!)
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)
                if (locationResult.locations.isNotEmpty()) {
                    val location = locationResult.lastLocation
                    val mTdatas = MTdatas(
                        latitude = location!!.latitude,
                        longitude = location.longitude
                    )
                    Log.i("long" , "${location.longitude}")
                    Log.i("reach", "ed")
                    action(mTdatas)
                }
            }
        }
        locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY,5000L)
            .build()
        /*locationRequest.interval = 50000
        locationRequest.fastestInterval = 50000
        locationRequest.smallestDisplacement = 170f // 170 m = 0.1 mile
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY //set according to your app function*/
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

    // start receiving location update when activity  visible/foreground
    /*override fun onResume() {
        super.onResume()
        startLocationUpdates()
    }*/

}
