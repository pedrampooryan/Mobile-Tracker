package com.example.mobiletracker

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

private const val Base_URL = "https://demo.thingsboard.io/"

private val client = OkHttpClient.Builder().build()

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(Base_URL)
    .client(client)
    .build()

interface MTApiService {
    @Headers("Content-Type: application/json")
    @POST("api/v1/jDJ0JbPfilNj8m4g6oAd/telemetry")
    fun sendLocation(@Body mTdatas: MTdatas): Call<MTdatas>
}

object MTApi {
    val retrofitService: MTApiService by lazy { retrofit.create(MTApiService::class.java) }
}