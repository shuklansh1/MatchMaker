package com.example.data.person.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PersonResponse(
    val info: Info,
    val results: List<Result>
)