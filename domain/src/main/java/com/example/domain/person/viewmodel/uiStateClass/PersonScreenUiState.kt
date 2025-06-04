package com.example.domain.person.viewmodel.uiStateClass

import com.example.domain.person.model.PersonResponseModel
import com.example.domain.person.model.Result

data class PersonScreenUiState(
    val listOfPersonsResponse: PersonResponseModel? = null,
    val likedUsers: MutableList<Result>? = null,
    val rejectedUsers: MutableList<Result>? = null,
)
