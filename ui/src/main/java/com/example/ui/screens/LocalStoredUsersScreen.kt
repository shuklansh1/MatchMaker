package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.domain.common.toResultsModel
import com.example.domain.local.viewmodel.LocalDataScreenViewmodel
import com.example.ui.components.PersonItemComposable

@Composable
fun LocalStoredUsersScreen(
    navController: NavHostController,
    viewModel: LocalDataScreenViewmodel = hiltViewModel()
) {
    BackHandler {
        navController.popBackStack()
    }

    val localResponseDataState = viewModel.dbResponseListState.collectAsState()

    BackHandler {
        navController.popBackStack()
    }

    LaunchedEffect(Unit) {
        viewModel.fetchPersonsDataFromDatabase()
    }
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically)
        ) {
            LazyColumn {
                items(localResponseDataState.value?.map {
                    it.toResultsModel()
                }.orEmpty()) {
                    PersonItemComposable(
                        it,
                        onAccept = {
                            // i don't know the behaviour to be added here :P
                        },
                        onReject = {
                            viewModel.removeUserFromLocalDb(it)
                        }
                    )
                }
            }
        }
    }
}