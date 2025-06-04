package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.domain.person.viewmodel.PersonsScreenViewModel
import com.example.ui.components.OnErrorStateRenderer
import com.example.ui.components.OnLoadingStateRenderer
import com.example.ui.components.OnSuccessStateRenderer
import com.example.ui.components.PersonItemComposable

@Composable
fun LazyColumnScreen(
    navController: NavHostController,
    viewModel: PersonsScreenViewModel = hiltViewModel()
) {
    BackHandler {
        navController.popBackStack()
    }

    val uiState = viewModel.uiState

    BackHandler {
        navController.popBackStack()
    }

    LaunchedEffect(Unit) {
        viewModel.fetchPersonsData()
    }

    OnSuccessStateRenderer(uiState) { uiState ->
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically)
        ) {
            LazyColumn {
                items(uiState.listOfPersonsResponse?.results.orEmpty()) {
                    AnimatedVisibility(
                        uiState.listOfPersonsResponse?.results?.contains(it) == true,
                        enter = expandVertically(),
                        exit = shrinkVertically()
                    ) {
                        PersonItemComposable(
                            it,
                            onAccept = {
                                viewModel.likeUser(it)
                            },
                            onReject = {
                                viewModel.rejectUser(it)
                            }
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                }
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