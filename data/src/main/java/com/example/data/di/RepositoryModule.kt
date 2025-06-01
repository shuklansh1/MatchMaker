package com.example.data.di

import com.example.data.person.repository.PersonDataRepositoryImpl
import com.example.domain.person.repository.PersonDataRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun providesPersonDataRepository(
        personDataRepositoryImpl: PersonDataRepositoryImpl
    ): PersonDataRepository
}