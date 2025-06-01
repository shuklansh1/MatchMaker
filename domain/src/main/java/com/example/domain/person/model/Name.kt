package com.example.domain.person.model

data class Name(
    val first: String,
    val last: String? = null,
    val title: String? = null,
)