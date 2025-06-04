package com.example.domain.local.usecase

import com.example.domain.local.repository.LocalStorageRepository
import com.example.domain.person.model.Result
import javax.inject.Inject

class LocalStorageUseCase @Inject constructor(
    private val localStorageRepository: LocalStorageRepository
) {
    suspend fun upsertMatchPerson(match: List<Result>) =
        localStorageRepository.upsertMatchPerson(match)

    suspend fun getAllDbMatchPerson() = localStorageRepository.getAllDbMatchPerson()

    suspend fun removePersonFromDb(personToRemove: Result) =
        localStorageRepository.removePersonFromDb(personToRemove)

    suspend fun addPersonToDb(personToAdd: Result) =
        localStorageRepository.addPersonToDb(personToAdd)
}