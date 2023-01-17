package com.example.mobiletracker.networkAndLocation

import javax.inject.Inject

class Repository @Inject constructor(private val MTApi: MTApiService) {

    suspend fun sendLocCoordinate(mTdatas: MTdatas) {
        MTApi.sendLocation(mTdatas)
    }
}