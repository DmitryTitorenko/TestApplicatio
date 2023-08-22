package com.example.testapplication.network

import com.example.testapplication.response.DevicesResult
import retrofit2.Response
import retrofit2.http.GET

interface Api {
    companion object {
        const val BASE_URL = "https://veramobile.mios.com/test_android/"
    }

    @GET("items.test")
    suspend fun loadDevices(): Response<DevicesResult>
}
