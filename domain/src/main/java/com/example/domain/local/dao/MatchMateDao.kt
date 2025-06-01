package com.example.domain.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.domain.local.model.ResultsModel
import kotlinx.coroutines.flow.Flow

@Dao
interface MatchMateDao {
    @Upsert
    @Transaction
    suspend fun upsertMatchPerson(match: List<ResultsModel>)

    @Query("DELETE FROM results_table")
    @Transaction
    suspend fun deleteAllMatchPerson()

    @Query("SELECT * FROM results_table")
    suspend fun getAllMatchPerson(): List<ResultsModel>

    @Query("SELECT * FROM results_table WHERE id = :id")
    suspend fun getMatchedPersonById(id: Int?): ResultsModel?

    @Query("SELECT * FROM results_table WHERE id = :id")
    fun getUserById(id: Int?): Flow<ResultsModel?>
}