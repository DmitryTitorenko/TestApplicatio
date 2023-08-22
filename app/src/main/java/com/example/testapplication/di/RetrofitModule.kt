package com.example.testapplication.di

import com.example.testapplication.network.Api
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RetrofitModule {

    @Singleton
    @Provides
    fun buildRetrofitConfig(): Api {
        val httpClient = getBaseHttpConfig()
        val retrofit = Retrofit.Builder()
            .baseUrl(Api.BASE_URL)
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        return retrofit.build().create(Api::class.java)
    }

    private fun getBaseHttpConfig(): OkHttpClient.Builder {
        val log = HttpLoggingInterceptor()
        log.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.addInterceptor(log)
        return okHttpClient
    }
}
