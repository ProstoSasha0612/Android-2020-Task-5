package com.hfad.android.funnycats.data

import android.media.Image
import android.util.Log
import androidx.paging.toLiveData
import com.hfad.android.funnycats.model.Cat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException
import java.lang.Exception

object CatApiImpl {
    private const val BASE_URL = "https://api.thecatapi.com/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val catService = retrofit.create(CatApi::class.java)
}