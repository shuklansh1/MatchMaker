package com.example.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.person.model.Result

@Composable
fun RecyclerViewComponent(
    people: List<Result>
) {
    val viewAdapter = remember {
        mutableStateOf(
            PersonAdapter(mutableListOf())
        )
    }
    AndroidView(
        modifier = Modifier.fillMaxWidth(),
        factory = { ctx ->
            RecyclerView(ctx).apply {
                layoutManager = LinearLayoutManager(ctx)
                adapter = viewAdapter.value
                setHasFixedSize(true)
            }
        },
        update = {
            viewAdapter.value.updatePeople(people)
        }
    )
}