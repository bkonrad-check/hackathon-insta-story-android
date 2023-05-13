package de.check24.hackathon.instagramstory.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitClient() {

    val builder: OkHttpClient = OkHttpClient.Builder().build()

    private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dev.whost.ml/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun createService(): ApiService = provideRetrofit(builder).create(ApiService::class.java)

}