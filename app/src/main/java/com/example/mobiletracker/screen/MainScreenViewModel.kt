package com.example.mobiletracker.screen


import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mobiletracker.networkAndLocation.LocationProvider
import com.example.mobiletracker.networkAndLocation.MTdatas
import com.example.mobiletracker.networkAndLocation.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val locationProvider: LocationProvider,
    private val repository: Repository,
    application: Application,
) : AndroidViewModel(application) {

    private val _locCoordinateAndID = MutableLiveData<MTdatas>()
    val locCoordinateAndID: LiveData<MTdatas>
        get() = _locCoordinateAndID


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
            _locCoordinateAndID.value = it
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












