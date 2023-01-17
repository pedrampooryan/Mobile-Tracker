package com.example.mobiletracker

import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface MTApiService {
    @Headers("Content-Type: application/json")
    @POST("api/v1/jDJ0JbPfilNj8m4g6oAd/telemetry")
    suspend fun sendLocation(@Body mTdatas: MTdatas): MTdatas
}

