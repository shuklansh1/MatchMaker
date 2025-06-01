package com.example.data.person.repository

import com.example.data.ApiClient
import com.example.data.person.mapper.toModel
import com.example.domain.common.toResultsDbModel
import com.example.domain.common.toResultsModel
import com.example.domain.local.dao.MatchMateDao
import com.example.domain.local.model.ResultsModel
import com.example.domain.person.model.PersonResponseModel
import com.example.domain.person.model.Result
import com.example.domain.person.repository.PersonDataRepository
import javax.inject.Inject

class PersonDataRepositoryImpl @Inject constructor(
    private val apiClient: ApiClient,
    private val matchMate: MatchMateDao,
) : PersonDataRepository {
    override suspend fun getPersonsData(): PersonResponseModel {
        return apiClient.getPersonsList().toModel()
    }

    override suspend fun getMatchedPersonById(id: String?): Result? {
        val matchedData = matchMate.getMatchedPersonById(
            id
        )
        return matchedData?.toResultsModel()
    }

    override suspend fun deleteAllMatchPerson() {
        matchMate.deleteAllMatchPerson()
    }

    override suspend fun upsertMatchPerson(match: List<Result>) {
        matchMate.upsertAllPeople(
            match.filter {
                it.picture?.large != null
            }.map {
                ResultsModel(
                    firstName = it.name.first,
                    lastName = it.name.last.orEmpty(),
                    image = it.picture?.large!!,
                    age = it.dob?.age ?: 0,
                )
            }
        )
    }

    override suspend fun getAllDbMatchPerson(): List<ResultsModel> {
        return matchMate.getAllMatchPerson()
    }

    override suspend fun removePersonFromDb(person: Result) {
        return matchMate.removePersonFromDb(person.toResultsDbModel().image)
    }

    override suspend fun addPersonToDb(person: Result) {
        matchMate.addPersonToDb(
            person.toResultsDbModel().firstName,
            person.toResultsDbModel().lastName,
            person.toResultsDbModel().image,
            person.toResultsDbModel().age,
        )
    }
}