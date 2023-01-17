package com.example.mobiletracker

import javax.inject.Inject

class Repository @Inject constructor(private val MTApi: MTApiService) {

    suspend fun sendLocCoordinate(mTdatas: MTdatas) {
        MTApi.sendLocation(mTdatas)
    }
}