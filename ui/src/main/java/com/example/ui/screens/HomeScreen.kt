package com.example.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.ui.R
import com.example.ui.navigation.LazyColumnScreen
import com.example.ui.navigation.RecyclerViewScreen

@Composable
fun HomeScreen(
    navController: NavHostController,
) {
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
            Button(onClick = {
                navController.navigate(RecyclerViewScreen)
            }) {
                Text(text = stringResource(R.string.view_recyclerview_screen))
            }
            Button(onClick = {
                navController.navigate(LazyColumnScreen)
            }) {
                Text(text = stringResource(R.string.view_lazycolumn_screen))
            }
        }
    }
}