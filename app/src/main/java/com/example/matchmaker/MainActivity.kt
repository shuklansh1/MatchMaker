package com.example.matchmaker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ui.screens.PersonsDataRecyclerViewScreen
import com.example.matchmaker.ui.theme.MatchMakerTheme
import com.example.ui.NavGraph
import com.example.ui.navigation.Home
import com.example.ui.navigation.LazyColumnScreen
import com.example.ui.navigation.RecyclerViewScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.LazyColumnScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MatchMakerTheme {
                val appNavController = rememberNavController()
                NavGraph(
                    appNavController
                )
            }
        }
    }
}
