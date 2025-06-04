package com.example.ui.components

import androidx.compose.runtime.Composable
import com.example.domain.common.UiState

@Composable
fun <T> OnSuccessStateRenderer(uiState: UiState<T>, composableToRender: @Composable (successState: T) -> Unit) {
    if (uiState is UiState.Success) {
        composableToRender(uiState.data)
    }
}

@Composable
fun <T> OnLoadingStateRenderer(uiState: UiState<T>, composableToRender: @Composable () -> Unit) {
    if (uiState is UiState.Loading) {
        composableToRender()
    }
}

@Composable
fun <T> OnErrorStateRenderer(uiState: UiState<T>, composableToRender: @Composable () -> Unit) {
    if (uiState is UiState.Error) {
        composableToRender()
    }
}