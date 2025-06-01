package com.example.data

import com.example.data.person.dto.PersonResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiClient {
    @GET("api/")
    suspend fun getPersonsList(
        @Query("results") results: Int = 10,
    ): PersonResponse
}