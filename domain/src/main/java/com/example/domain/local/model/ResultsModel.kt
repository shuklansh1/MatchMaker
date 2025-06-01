package com.example.domain.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "results_table")
data class ResultsModel(
    @PrimaryKey val id: Int,
    val firstName: String,
    val lastName: String,
    val image: String,
    val age: Int,
)