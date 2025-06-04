package com.example.domain.common

sealed class UiState<out T> {
    data object Loading : UiState<Nothing>()
    class Success<T>(val data: T) : UiState<T>()
    class Error(val error: Throwable) : UiState<Nothing>()
}

fun <T, R> UiState<T>.whenSuccess(onSuccess: T.() -> R?): R? {
    if (this is UiState.Success) {
        return onSuccess(this.data)
    }

    return null
}
