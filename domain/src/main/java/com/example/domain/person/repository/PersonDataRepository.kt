package com.example.domain.person.repository

import com.example.domain.person.model.PersonResponseModel

interface PersonDataRepository {
    suspend fun getPersonsData(size: Int): PersonResponseModel
}