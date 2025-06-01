package com.example.data.person.repository

import com.example.data.ApiClient
import com.example.data.person.mapper.toModel
import com.example.domain.local.dao.MatchMateDao
import com.example.domain.local.model.ResultsModel
import com.example.domain.person.model.Dob
import com.example.domain.person.model.Name
import com.example.domain.person.model.PersonResponseModel
import com.example.domain.person.model.Picture
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

    override suspend fun getMatchedPersonById(id: Int?): Result? {
        val matchedData = matchMate.getMatchedPersonById(
            id
        )
        return Result(
            name = Name(
                first = matchedData?.firstName.orEmpty(),
                last = matchedData?.lastName.orEmpty(),
            ),
            picture = Picture(
                large = matchedData?.image.orEmpty(),
            ),
            dob = Dob(
                age = matchedData?.age ?: 0,
            )
        )
    }

    override suspend fun deleteAllMatchPerson() {
        matchMate.deleteAllMatchPerson()
    }

    override suspend fun upsertMatchPerson(match: List<Result>) {
        matchMate.upsertMatchPerson(
            match.map {
                ResultsModel(
                    id = 0,
                    firstName = it.name.first,
                    lastName = it.name.last.orEmpty(),
                    image = it.picture?.large.orEmpty(),
                    age = it.dob?.age ?: 0,
                )
            }
        )
    }
}