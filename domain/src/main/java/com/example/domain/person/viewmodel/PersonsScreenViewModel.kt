package com.example.domain.person.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.domain.common.StateViewModel
import com.example.domain.common.UiState
import com.example.domain.common.whenSuccess
import com.example.domain.local.usecase.LocalStorageUseCase
import com.example.domain.person.model.PersonResponseModel
import com.example.domain.person.model.Result
import com.example.domain.person.usecase.GetPersonsDataUseCase
import com.example.domain.person.viewmodel.uiStateClass.PersonScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonsScreenViewModel @Inject constructor(
    private val personsUseCase: GetPersonsDataUseCase,
    private val localStorageUseCase: LocalStorageUseCase
) : StateViewModel<PersonScreenUiState>() {

    fun updateUiState(
        newListOfPersonsResponse: PersonResponseModel? = null,
        newLikedUsers: MutableList<Result>? = null,
        newRejectedUsers: MutableList<Result>? = null,
    ) {
        uiState = UiState.Success(
            PersonScreenUiState(
                newListOfPersonsResponse,
                newLikedUsers,
                newRejectedUsers
            )
        )
    }

    fun fetchPersonsData(size: Int = 10) {
        viewModelScope.launch {
            runCatching { personsUseCase.invoke(size) }
                .fold({
                    if (!uiState.whenSuccess { likedUsers }
                            .isNullOrEmpty() || !uiState.whenSuccess { rejectedUsers }
                            .isNullOrEmpty()) {
                        val updatedResults = uiState.whenSuccess { listOfPersonsResponse }?.results
                        updatedResults?.addAll(
                            it.results
                        )
                        updatedResults?.removeAll {
                            uiState.whenSuccess { likedUsers }?.contains(it) == true &&
                                    uiState.whenSuccess { rejectedUsers }?.contains(it) == true
                        }
                        updateUiState(
                            newListOfPersonsResponse = uiState.whenSuccess { listOfPersonsResponse }
                                ?.copy(
                                    results = updatedResults.orEmpty().toMutableList()
                                ) ?: it
                        )
                    } else {
                        updateUiState(newListOfPersonsResponse = it)
                    }
                }, {
                    updateUiState(newListOfPersonsResponse = null)
                })
        }
    }

    fun likeUser(user: Result) {
        viewModelScope.launch {
            if (uiState.whenSuccess { likedUsers } != null) {
                uiState.whenSuccess { likedUsers }?.add(user)
            } else {
                updateUiState(newLikedUsers = mutableListOf(user))
            }
            var updatedResults =
                uiState.whenSuccess { listOfPersonsResponse }?.results.orEmpty().toMutableList()
            updatedResults.removeAll { it == user }
            updateUiState(
                newListOfPersonsResponse =
                    uiState.whenSuccess { listOfPersonsResponse }?.copy(
                        results = updatedResults
                    )
            )
            addPersonToDb(user)
            fetchPersonsData(1) // fetch new data
        }
    }

    fun addPersonToDb(user: Result) {
        viewModelScope.launch {
            localStorageUseCase.addPersonToDb(user)
        }
    }

    fun rejectUser(user: Result) {
        viewModelScope.launch {
            if (uiState.whenSuccess { likedUsers } != null) {
                uiState.whenSuccess { rejectedUsers }?.add(user)
            } else {
                updateUiState(newRejectedUsers = mutableListOf(user))
            }
            var updatedResults =
                uiState.whenSuccess { listOfPersonsResponse }?.results.orEmpty().toMutableList()
            updatedResults.removeAll { it == user }
            updateUiState(
                newListOfPersonsResponse =
                    uiState.whenSuccess { listOfPersonsResponse }?.copy(
                        results = updatedResults
                    )
            )
            fetchPersonsData(1) // fetch new data
        }
    }
}