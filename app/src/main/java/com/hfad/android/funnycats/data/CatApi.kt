package com.hfad.android.funnycats.data

import retrofit2.Response
import retrofit2.http.GET

public interface CatApi {
    //TODO move api key or try without him
    @GET("v1/images/search?limit=10")
    suspend fun getCatsList(): Response<List<CatApiData>>

    companion object {
        const val MAX_PAGE_SIZE = 10
    }
}

