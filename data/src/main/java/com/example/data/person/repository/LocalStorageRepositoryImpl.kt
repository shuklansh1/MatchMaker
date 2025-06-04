package com.example.data.person.repository

import com.example.domain.common.toResultsDbModel
import com.example.domain.common.toResultsModel
import com.example.domain.local.dao.MatchMateDao
import com.example.domain.local.model.ResultsModel
import com.example.domain.local.repository.LocalStorageRepository
import com.example.domain.person.model.Result
import javax.inject.Inject

class LocalStorageRepositoryImpl @Inject constructor(
    private val matchMate: MatchMateDao,
): LocalStorageRepository  {
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