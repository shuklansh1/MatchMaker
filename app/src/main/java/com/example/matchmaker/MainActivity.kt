package com.example.matchmaker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.example.matchmaker.ui.theme.MatchMakerTheme
import com.example.ui.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MatchMakerTheme {
                Column(modifier = Modifier.fillMaxSize()) {
                    val people = listOf(
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
                    AndroidView(
                        factory = { ctx ->
                            RecyclerView(ctx).apply {
                                layoutManager = LinearLayoutManager(ctx)
                                adapter = PersonAdapter(
                                    people
                                )
                                setHasFixedSize(true)
                            }
                        },
                    )
                }
            }
        }
    }
}

class PersonAdapter(private val people: List<Person>) :
    RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {

    class PersonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(com.example.ui.R.id.tvName)
        val age: TextView = itemView.findViewById(com.example.ui.R.id.tvAge)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PersonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_person, parent, false)
        return PersonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = people[position]
        holder.name.text = person.name
        holder.age.text = "Age: ${person.age}"
    }

    override fun getItemCount(): Int = people.size
}

data class Person(
    val name: String,
    val age: Int,
    val email: String
)