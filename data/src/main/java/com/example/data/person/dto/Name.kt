package com.example.data.person.dto

data class Name(
    val first: String,
    val last: String? = null,
    val title: String? = null,
)