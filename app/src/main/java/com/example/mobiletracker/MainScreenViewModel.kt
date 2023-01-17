package com.example.mobiletracker


import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val locationProvider: LocationProvider,
    private val repository: Repository,
    application: Application,
) : AndroidViewModel(application) {


    private fun sendLocationAtt(mTdatas: MTdatas) {
        Log.i("message=", "function started")
        viewModelScope.launch {
            try {
                repository.sendLocCoordinate(mTdatas)
            } catch (_: Throwable) {
            }
        }
    }

    fun listenToLocation() {
        locationProvider.getLocation {
            sendLocationAtt(it)
        }
    }

    fun startLocationListener() {
        locationProvider.startLocationUpdates()
    }

    fun stopLocationListener() {
        locationProvider.stopLocationUpdates()
    }
}












