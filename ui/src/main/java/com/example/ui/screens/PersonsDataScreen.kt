package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.matchmaker.screens.viewmodel.PersonsScreenViewModel
import com.example.ui.components.RecyclerViewComponent

@Composable
fun PersonsDataRecyclerViewScreen(
    navController: NavHostController,
    viewModel: PersonsScreenViewModel = hiltViewModel()
) {
    BackHandler {
        navController.popBackStack()
    }

    val responseDataState = viewModel.responseDataState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchPersonsData()
    }

    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RecyclerViewComponent(responseDataState.value?.results.orEmpty())
        }
    }
}