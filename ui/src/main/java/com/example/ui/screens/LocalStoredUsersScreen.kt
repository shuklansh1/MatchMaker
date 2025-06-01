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
import com.example.domain.person.model.Dob
import com.example.domain.person.model.Name
import com.example.domain.person.model.Picture
import com.example.domain.person.model.Result
import com.example.matchmaker.screens.viewmodel.PersonsScreenViewModel
import com.example.ui.components.PersonItemComposable
import kotlin.collections.orEmpty

@Composable
fun LocalStoredUsersScreen(
    navController: NavHostController,
    viewModel: PersonsScreenViewModel = hiltViewModel()
) {
    BackHandler {
        navController.popBackStack()
    }

    val localResponseDataState = viewModel.dbResponseListState.collectAsState()

    BackHandler {
        navController.popBackStack()
    }

    LaunchedEffect(localResponseDataState.value?.size) {
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
                    Result(
                        name = Name(
                            first = it.firstName,
                            last = it.lastName
                        ),
                        picture = Picture(
                            large = it.image
                        ),
                        dob = Dob(
                            age = it.age
                        )
                    )
                }.orEmpty()) {
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
            }
        }
    }
}