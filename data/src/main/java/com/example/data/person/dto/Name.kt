package com.example.data.person.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Name(
    val first: String,
    val last: String? = null,
    val title: String? = null,
)