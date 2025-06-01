package com.example.domain.di

import android.content.Context
import com.example.domain.local.db.MatchMateDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class DomainModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context) =
        MatchMateDatabase.getDatabase(context)

    @Provides
    fun provideMatchMateDao(appDatabase: MatchMateDatabase) = appDatabase.matchMateDao()
}