package com.example.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ui.navigation.Home
import com.example.ui.navigation.LazyColumnScreen
import com.example.ui.navigation.RecyclerViewScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.LazyColumnScreen
import com.example.ui.screens.PersonsDataRecyclerViewScreen

@Composable
fun NavGraph(appNavController: NavHostController) {
    NavHost(
        navController = appNavController,
        startDestination = Home
    ) {
        composable<Home> {
            HomeScreen(
                appNavController
            )
        }

        composable<RecyclerViewScreen> {
            PersonsDataRecyclerViewScreen(
                navController = appNavController
            )
        }

        composable<LazyColumnScreen> {
            LazyColumnScreen(
                navController = appNavController
            )
        }
    }
}