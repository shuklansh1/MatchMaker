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

    private var _responseList = MutableStateFlow<PersonResponseModel?>(null)
    val responseDataState = _responseList.asStateFlow()

    private var _likedUsers = MutableStateFlow<MutableList<Result>?>(null)
    val likedUsersDataState = _likedUsers.asStateFlow()

    private var _rejectedUsers = MutableStateFlow<MutableList<Result>?>(null)
    val rejectedUsersDataState = _rejectedUsers.asStateFlow()

    fun fetchPersonsData() {
        viewModelScope.launch {
            runCatching { personsUseCase.invoke() }
                .fold({
                    val data = it
                    if (!_likedUsers.value.isNullOrEmpty() || !_rejectedUsers.value.isNullOrEmpty()) {
                        val updatedData = data.copy(
                            results = data.results.filterNot {
                                _likedUsers.value?.contains(it) == true &&
                                        _rejectedUsers.value?.contains(it) == true
                            }
                        )
                        _responseList.value = updatedData
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
            _responseList.value =
                _responseList.value?.copy(
                    results = _responseList.value?.results?.filter { it != user }.orEmpty()
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
            _responseList.value =
                _responseList.value?.copy(
                    results = _responseList.value?.results?.filter { it != user }.orEmpty()
                )
        }
    }
}