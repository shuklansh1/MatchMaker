package com.example.matchmaker

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.domain.person.model.Dob
import com.example.domain.person.model.Name
import com.example.matchmaker.ui.theme.MatchMakerTheme
import com.example.domain.person.model.Result
import com.example.matchmaker.screens.PersonsDataScreen
import com.example.matchmaker.screens.viewmodel.PersonsScreenViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MatchMakerTheme {
                Column(modifier = Modifier.fillMaxSize()) {
                    PersonsDataScreen()
                }
            }
        }
    }
}
