package com.example.matchmaker.screens.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.person.model.PersonResponseModel
import com.example.domain.person.repository.PersonDataRepository
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
    var responseDataState = _responseList.asStateFlow()

    fun fetchPersonsData() {
        viewModelScope.launch {
            runCatching { personsUseCase.invoke() }
                .fold({
                    _responseList.emit(it)
                }, {
                    _responseList.emit(null)
                })
        }
    }
}