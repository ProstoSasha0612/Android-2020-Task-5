package com.hfad.android.hotcats.data

import com.hfad.android.hotcats.model.Cat
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

fun CatApiData.toCat(): Cat {
    return Cat(url, id)
}