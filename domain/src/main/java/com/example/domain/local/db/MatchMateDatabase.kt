package com.example.domain.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.domain.local.dao.MatchMateDao
import com.example.domain.local.model.ResultsModel

@Database(entities = [ResultsModel::class], version = 1, exportSchema = false)
abstract class MatchMateDatabase : RoomDatabase() {
    abstract fun matchMateDao(): MatchMateDao

    companion object {
        @Volatile
        private var INSTANCE: MatchMateDatabase? = null

        fun getDatabase(context: Context): MatchMateDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MatchMateDatabase::class.java,
                    "results_table"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}