package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.domain.person.viewmodel.PersonsScreenViewModel
import com.example.ui.components.OnErrorStateRenderer
import com.example.ui.components.OnLoadingStateRenderer
import com.example.ui.components.OnSuccessStateRenderer
import com.example.ui.components.RecyclerViewComponent

@Composable
fun PersonsDataRecyclerViewScreen(
    navController: NavHostController,
    viewModel: PersonsScreenViewModel = hiltViewModel()
) {
    BackHandler {
        navController.popBackStack()
    }

    val uiState = viewModel.uiState

    LaunchedEffect(Unit) {
        viewModel.fetchPersonsData()
    }

    OnSuccessStateRenderer(uiState) { uiState ->
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RecyclerViewComponent(
                uiState.listOfPersonsResponse?.results.orEmpty()
            ) {
                viewModel.likeUser(it)
            }
        }
    }

    OnLoadingStateRenderer(uiState) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }

    OnErrorStateRenderer(uiState) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Something went wrong please try again")
        }
    }
}