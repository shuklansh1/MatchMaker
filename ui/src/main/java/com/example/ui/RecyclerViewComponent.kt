package com.example.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.person.model.Result

@Composable
fun RecyclerViewComponent(
    people: List<Result>
) {
    AndroidView(
        factory = { ctx ->
            RecyclerView(ctx).apply {
                layoutManager = LinearLayoutManager(ctx)
                adapter = PersonAdapter(
                    people = people
                )
                setHasFixedSize(true)
            }
        },
    )
}