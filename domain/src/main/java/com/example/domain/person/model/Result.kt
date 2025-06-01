package com.example.domain.person.model

data class Result(
    val name: Name,
    val dob: Dob? = null,
    val cell: String? = null,
    val email: String? = null,
    val gender: String? = null,
    val id: Id? = null,
    val location: Location? = null,
    val login: Login? = null,
    val nat: String? = null,
    val phone: String? = null,
    val picture: Picture? = null,
    val registered: Registered? = null,
)