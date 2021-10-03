package com.hfad.android.hotcats.data

import android.media.Image
import android.util.Log
import com.hfad.android.hotcats.model.Cat
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

    private val catService = retrofit.create(CatApi::class.java)


    suspend fun getCats(): MutableList<Cat> {
        return withContext(Dispatchers.IO) {
            val catApiDataList = catService.getCatsList()
            val catList = mutableListOf<Cat>()
            for (i in catApiDataList) {
                catList.add(Cat(i.url, "Empty0"))
            }
            catList
        }
    }

    suspend fun getCat(): Cat {
        return withContext(Dispatchers.IO) {

            val catApiData = catService.getCat()
            val imageUrl = catApiData[0].url
            Cat(imageUrl, "Empty)")
        }
    }
}