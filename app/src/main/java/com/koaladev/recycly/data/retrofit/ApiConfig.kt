package com.koaladev.recycly.data.retrofit

import com.koaladev.recycly.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiConfig {
    private const val BASE_URL = BuildConfig.BASE_URL

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    private val instance: ApiService by lazy {
        Retrofit.Builder()
           .baseUrl(BASE_URL)
           .client(client)
           .addConverterFactory(GsonConverterFactory.create())
           .build()
           .create(ApiService::class.java)
    }

    fun getApiService(): ApiService = instance
}