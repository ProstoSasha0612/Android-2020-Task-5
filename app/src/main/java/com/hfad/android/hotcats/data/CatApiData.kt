package com.hfad.android.hotcats.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CatApiData(
    @Json(name = "url")
    val url: String?,
    @Json(name = "id")
    val id: String?,
    @Json(name = "width")
    val width: String?,
    @Json(name = "height")
    val height: String?
)