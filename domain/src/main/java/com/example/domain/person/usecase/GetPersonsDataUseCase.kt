package com.example.domain.person.usecase

import com.example.domain.person.model.PersonResponseModel
import com.example.domain.person.model.Result
import com.example.domain.person.repository.PersonDataRepository
import javax.inject.Inject

class GetPersonsDataUseCase @Inject constructor(
    private val personRepository: PersonDataRepository
) {
    suspend operator fun invoke(): PersonResponseModel = personRepository.getPersonsData()

    suspend fun upsertMatchPerson(match: List<Result>) = personRepository.upsertMatchPerson(match)

    suspend fun getAllDbMatchPerson() = personRepository.getAllDbMatchPerson()
}