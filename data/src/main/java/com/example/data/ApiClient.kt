package com.example.data

import com.example.data.person.dto.PersonResponse
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiClient {
    @GET
    suspend fun getPersonsList(
        @Url url: String = "api/?results=10",
    ): PersonResponse
}