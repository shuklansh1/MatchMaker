package com.example.matchmaker.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.matchmaker.screens.viewmodel.PersonsScreenViewModel
import com.example.ui.RecyclerViewComponent

@Composable
fun PersonsDataScreen(
    viewModel: PersonsScreenViewModel = hiltViewModel()
) {
    val responseDataState = viewModel.responseDataState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchPersonsData()
    }

    Scaffold {
        Column(modifier = Modifier.fillMaxSize().padding(it)) {
            RecyclerViewComponent(responseDataState.value?.results.orEmpty())
        }
    }
}