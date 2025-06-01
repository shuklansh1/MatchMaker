package com.example.domain.person.repository

import com.example.domain.local.model.ResultsModel
import com.example.domain.person.model.PersonResponseModel
import com.example.domain.person.model.Result

interface PersonDataRepository {
    suspend fun getPersonsData(): PersonResponseModel

    suspend fun getMatchedPersonById(id: Int?): Result?

    suspend fun deleteAllMatchPerson()

    suspend fun upsertMatchPerson(match: List<Result>)

    suspend fun getAllDbMatchPerson(): List<ResultsModel>
}