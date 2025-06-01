package com.example.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.person.dto.Person

@Composable
fun RecyclerViewComponent() {
    AndroidView(
        factory = { ctx ->
            RecyclerView(ctx).apply {
                layoutManager = LinearLayoutManager(ctx)
                adapter = PersonAdapter(
                    listOf(
                        Person("John Doe", 30, "james.wilson@example-pet-store.com"),
                        Person("John Poe", 31, "william.jogh.harrison@example-pet-store.com"),
                        Person(
                            "John Croe",
                            37,
                            "william.jogmgm.harrison@example-pet-store.com"
                        ),
                        Person(
                            "John Shmroe",
                            37,
                            "william.jogmgm.harrison@example-pet-store.com"
                        )
                    )
                )
                setHasFixedSize(true)
            }
        },
    )
}