package com.example.data.person.repository

import com.example.data.ApiClient
import com.example.data.person.mapper.toModel
import com.example.domain.person.model.PersonResponseModel
import com.example.domain.person.repository.PersonDataRepository
import javax.inject.Inject

class PersonDataRepositoryImpl @Inject constructor(
    private val apiClient: ApiClient
): PersonDataRepository {
    override suspend fun getPersonsData(): PersonResponseModel {
        return apiClient.getPersonsList().toModel()
    }
}