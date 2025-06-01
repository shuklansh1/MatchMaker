package com.example.data.person.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Dob(
    val age: Int,
    val date: String? = null
)