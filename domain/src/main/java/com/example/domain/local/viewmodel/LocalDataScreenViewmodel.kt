package com.example.domain.local.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.local.model.ResultsModel
import com.example.domain.person.model.Result
import com.example.domain.person.usecase.GetPersonsDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocalDataScreenViewmodel @Inject constructor(
    private val personsUseCase: GetPersonsDataUseCase
) : ViewModel() {

    private var _dbResponseList =
        MutableStateFlow<List<ResultsModel>?>(emptyList())
    val dbResponseListState = _dbResponseList.asStateFlow()


    fun fetchPersonsDataFromDatabase() {
        viewModelScope.launch {
            runCatching { personsUseCase.getAllDbMatchPerson() }
                .fold({
                    _dbResponseList.emit(it)
                }, {
                    _dbResponseList.emit(null)
                }
            )
        }
    }

    fun removeUserFromLocalDb(user: Result) {
        viewModelScope.launch {
            runCatching {
                personsUseCase.removePersonFromDb(user)
            }
            .fold(
                {
                    // to update the list if succcesss
                    fetchPersonsDataFromDatabase()
                }, {
                    // handle error here
                }
            )
        }
    }
}