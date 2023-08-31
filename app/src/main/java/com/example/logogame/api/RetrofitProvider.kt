package com.example.logogame.api

import com.example.logogame.data.LogoQuizResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

object RetrofitFactory {
    private val okHttpClient =  OkHttpClient.Builder().addNetworkInterceptor(
        HttpLoggingInterceptor(
            HttpLoggingInterceptor.Logger {
            }).setLevel(HttpLoggingInterceptor.Level.BASIC))
        .build()

    private val retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("https://run.mocky.io")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getService() = retrofit.create(LogoGameService::class.java)
}

interface LogoGameService  {

    @GET("/v3/97b90ec1-e900-443d-a440-2051a0a9891e")
    suspend fun getLogoQuiz(): LogoQuizResponse
}