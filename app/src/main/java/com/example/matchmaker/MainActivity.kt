package com.example.matchmaker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.example.domain.person.model.Dob
import com.example.domain.person.model.Name
import com.example.matchmaker.ui.theme.MatchMakerTheme
import com.example.domain.person.model.Result
import com.example.ui.RecyclerViewComponent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MatchMakerTheme {
                Column(modifier = Modifier.fillMaxSize()) {
                    val people = listOf(
                        Result(
                            Name("John Doe"),
                            Dob(12)
                        ),
                        Result(
                            Name("Jane Doe"),
                            Dob(22)
                        ),
                        Result(
                            Name("Peter Doe"),
                            Dob(32)
                        ),
                        Result(
                            Name("Edward Doe"),
                            Dob(18)
                        ),
                        Result(
                            Name("Joseph Doe"),
                            Dob(28)
                        ),
                        Result(
                            Name("Collin Doe"),
                            Dob(25)
                        ),
                    )
                    RecyclerViewComponent(people)
                }
            }
        }
    }
}
