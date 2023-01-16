package com.example.mobiletracker


import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val locationProvider: LocationProvider,
    application: Application
) : AndroidViewModel(application) {

    private fun sendLocationAtt(mTdatas: MTdatas) {
        Log.i("message=", "function started")
        MTApi.retrofitService.sendLocation(mTdatas).enqueue(object : Callback<MTdatas> {
            override fun onResponse(call: Call<MTdatas>, response: Response<MTdatas>) {
                Log.i("message=", "succeed")
            }
            override fun onFailure(call: Call<MTdatas>, t: Throwable) {
                Log.i("message=", "failed")
            }
        })
    }

    fun listenToLocation() {
       // getImei()
        locationProvider.getLocation {
            sendLocationAtt(it)
        }
    }

    /*@SuppressLint("HardwareIds")
    fun getImei() {
        val id: String = Secure.getString(getApplication<Application>().contentResolver, Secure.ANDROID_ID)
        Log.d("Pedram", id)
        //val id: String = Secure.getString(contentResolver, Secure.ANDROID_ID)
    }*/
}












