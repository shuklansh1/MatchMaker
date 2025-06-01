package com.example.domain.person.model

data class PersonResponseModel(
    val results: MutableList<Result>,
    val info: Info? = null
)