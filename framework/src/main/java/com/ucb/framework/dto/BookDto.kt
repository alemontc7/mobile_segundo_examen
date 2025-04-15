package com.ucb.framework.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BookDto(
    val title: String,
    @Json(name = "author_name")
    val authors: List<String> = emptyList(),
    @Json(name = "first_publish_year")
    val publicationYear: Int = 0
)