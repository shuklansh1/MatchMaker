package com.example.domain.common

import com.example.domain.local.model.ResultsModel
import com.example.domain.person.model.Picture
import com.example.domain.person.model.Result

fun com.example.domain.person.model.Result.toResultsDbModel() = ResultsModel(
    firstName = name.first,
    lastName = name.last.orEmpty(),
    image = picture?.large.orEmpty(),
    age = dob?.age ?: 0,
)

fun ResultsModel.toResultsModel() = com.example.domain.person.model.Result(
    name = com.example.domain.person.model.Name(
        first = firstName.orEmpty(),
        last = lastName.orEmpty(),
    ),
    picture = Picture(
        large = image.toString(),
    ),
    dob = com.example.domain.person.model.Dob(
        age = age,
    )
)

fun Result.isGoodMatch(): Boolean {
    val userAge = this.dob?.age ?: 0
    return (userAge > 24 && userAge < 35)
}
