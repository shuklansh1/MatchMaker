package com.example.matchmaker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.example.matchmaker.ui.theme.MatchMakerTheme
import com.example.domain.person.model.PersonModel
import com.example.ui.RecyclerViewComponent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MatchMakerTheme {
                Column(modifier = Modifier.fillMaxSize()) {
                    val people = listOf(
                        PersonModel("John Doe", 30, "james.wilson@example-pet-store.com"),
                        PersonModel("John Poe", 31, "william.jogh.harrison@example-pet-store.com"),
                        PersonModel(
                            "John Croe",
                            37,
                            "william.jogmgm.harrison@example-pet-store.com"
                        ),
                        PersonModel(
                            "John Shmroe",
                            37,
                            "william.jogmgm.harrison@example-pet-store.com"
                        )
                    )
                    RecyclerViewComponent(people)
                }
            }
        }
    }
}
