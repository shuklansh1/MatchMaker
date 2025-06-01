package com.example.data.person.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Result(
    val name: Name,
    val dob: Dob? = null,
    val cell: String? = null,
    val email: String? = null,
    val gender: String? = null,
    val id: Id? = null,
    val picture: Picture? = null,
)