package com.example.mobiletracker


import android.util.Log
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainScreenViewModel : ViewModel() {

    init {
        sendLocationAtt()
    }

    private fun sendLocationAtt() {
        Log.i("message=", "function started")
        val mTdatas: MTdatas = MTdatas()
        MTApi.retrofitService.sendLocation(mTdatas).enqueue(object : Callback<MTdatas> {
            override fun onResponse(call: Call<MTdatas>, response: Response<MTdatas>) {
                Log.i("message=", "succeed")
            }

            override fun onFailure(call: Call<MTdatas>, t: Throwable) {
                Log.i("message=", "failed")
            }

        })
    }
}