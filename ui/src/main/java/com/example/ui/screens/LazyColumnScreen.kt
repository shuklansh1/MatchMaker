package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.matchmaker.screens.viewmodel.PersonsScreenViewModel
import com.example.ui.navigation.LazyColumnScreen
import kotlin.collections.orEmpty

@Composable
fun LazyColumnScreen(
    navController: NavHostController,
    viewModel: PersonsScreenViewModel = hiltViewModel()
) {
    BackHandler {
        navController.popBackStack()
    }

    val responseDataState = viewModel.responseDataState.collectAsState()

    BackHandler {
        navController.popBackStack()
    }

    LaunchedEffect(Unit) {
        viewModel.fetchPersonsData()
    }
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically)
        ) {
            LazyColumn {
                items(responseDataState.value?.results.orEmpty()) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AsyncImage(
                            it.picture?.large.orEmpty(),
                            contentDescription = null,
                            modifier = Modifier.fillMaxWidth(0.5f).height(240.dp)
                        )
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(text = it.name.first.orEmpty())
                            Text(text = it.name.last.orEmpty())
                        }
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Button({

                            }) {
                                Text("Accept",  color = Color.Green)
                            }
                            Button({

                            }) {
                                Text("Reject", color = Color.Red)
                            }
                        }
                    }
                }
            }
        }
    }
}