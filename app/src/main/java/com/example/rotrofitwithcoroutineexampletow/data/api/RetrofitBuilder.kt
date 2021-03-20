package com.example.rotrofitwithcoroutineexampletow.data.api

import com.example.rotrofitwithcoroutineexampletow.utils.Constance
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private val client: OkHttpClient = OkHttpClient.Builder().build()

    fun getRetrofit(): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create()).baseUrl(Constance.BASE_URL)
        .client(client).build()

    val apiService: ApiService = getRetrofit().create(ApiService::class.java)
}