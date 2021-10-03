package com.hfad.android.hotcats.data

import com.hfad.android.hotcats.model.Cat
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

public interface CatApi {
    //TODO move api key or try without him
    @GET("v1/images/search?limit=10"    )  //?api-key=03a4d368-630c-4a04-8b7c-28482f3dd392
    suspend fun getCatsList(): List<CatApiData>

    @GET("v1/images/search?limit=1")
    suspend fun getCat():List<CatApiData>
}

