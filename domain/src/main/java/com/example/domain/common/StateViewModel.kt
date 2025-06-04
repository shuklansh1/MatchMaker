package com.example.domain.common

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

abstract class StateViewModel<S: Any> : ViewModel() {

    var uiState by mutableStateOf<UiState<S>>(UiState.Loading)
        protected set

}