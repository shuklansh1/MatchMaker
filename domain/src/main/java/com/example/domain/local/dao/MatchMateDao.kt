package com.example.domain.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.domain.local.model.ResultsModel

@Dao
interface MatchMateDao {
    @Upsert
    @Transaction
    suspend fun upsertAllPeople(match: List<ResultsModel>)

    @Query("DELETE FROM results_table")
    @Transaction
    suspend fun deleteAllMatchPerson()

    @Query("SELECT * FROM results_table")
    suspend fun getAllMatchPerson(): List<ResultsModel>

    @Query("SELECT * FROM results_table WHERE image = :id")
    suspend fun getMatchedPersonById(id: String?): ResultsModel?

    @Query("DELETE FROM results_table WHERE image = :id")
    suspend fun removePersonFromDb(id: String?)

    @Query("INSERT INTO results_table (firstName, lastName, image, age) VALUES (:firstName, :lastName, :image, :age)")
    suspend fun addPersonToDb(
        firstName: String,
        lastName: String,
        image: String,
        age: Int
    )
}