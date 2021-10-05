package com.hfad.android.funnycats.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object CatApiImpl {
    private const val BASE_URL = "https://api.thecatapi.com/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val catService = retrofit.create(CatApi::class.java)
}