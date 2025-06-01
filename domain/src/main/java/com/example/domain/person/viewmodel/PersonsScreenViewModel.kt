package com.example.matchmaker.screens.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.person.model.PersonResponseModel
import com.example.domain.person.model.Result
import com.example.domain.person.usecase.GetPersonsDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonsScreenViewModel @Inject constructor(
    private val personsUseCase: GetPersonsDataUseCase
) : ViewModel() {

    private var _responseList =
        MutableStateFlow<PersonResponseModel?>(PersonResponseModel(mutableListOf()))
    val responseDataState = _responseList.asStateFlow()

    private var _likedUsers = MutableStateFlow<MutableList<Result>?>(mutableListOf())
    val likedUsersDataState = _likedUsers.asStateFlow()

    private var _rejectedUsers = MutableStateFlow<MutableList<Result>?>(mutableListOf())
    val rejectedUsersDataState = _rejectedUsers.asStateFlow()

    fun fetchPersonsData() {
        viewModelScope.launch {
            runCatching { personsUseCase.invoke() }
                .fold({
                    if (!_likedUsers.value.isNullOrEmpty() || !_rejectedUsers.value.isNullOrEmpty()) {
                        val updatedResults = _responseList.value?.results
                        updatedResults?.addAll(
                            it.results
                        )
                        updatedResults?.removeAll {
                            _likedUsers.value?.contains(it) == true &&
                                    _rejectedUsers.value?.contains(it) == true
                        }
                        _responseList.value = _responseList.value?.copy(
                            results = updatedResults.orEmpty().toMutableList()
                        ) ?: it
                    } else {
                        _responseList.value = it
                    }
                    if (_responseList.value?.results.orEmpty().isNotEmpty()) {
                        personsUseCase.upsertMatchPerson(
                            _responseList.value?.results.orEmpty()
                        )
                    }
                }, {
                    _responseList.emit(null)
                })
        }
    }

    fun likeUser(user: Result) {
        viewModelScope.launch {
            if (_likedUsers.value != null) {
                _likedUsers.value?.add(user)
            } else {
                _likedUsers.value = mutableListOf(user)
            }
            var updatedResults = _responseList.value?.results.orEmpty().toMutableList()
            updatedResults.removeAll { it == user }
            _responseList.value =
                _responseList.value?.copy(
                    results = updatedResults
                )
        }
    }

    fun rejectUser(user: Result) {
        viewModelScope.launch {
            if (_likedUsers.value != null) {
                _rejectedUsers.value?.add(user)
            } else {
                _rejectedUsers.value = mutableListOf(user)
            }
            var updatedResults = _responseList.value?.results.orEmpty().toMutableList()
            updatedResults.removeAll { it == user }
            _responseList.value =
                _responseList.value?.copy(
                    results = updatedResults
                )
        }
    }
}