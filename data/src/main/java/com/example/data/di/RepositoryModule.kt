package com.example.data.di

import com.example.data.person.repository.LocalStorageRepositoryImpl
import com.example.data.person.repository.PersonDataRepositoryImpl
import com.example.domain.local.repository.LocalStorageRepository
import com.example.domain.person.repository.PersonDataRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun providesPersonDataRepository(
        personDataRepositoryImpl: PersonDataRepositoryImpl
    ): PersonDataRepository

    @Binds
    fun providesLocalStorageRepository(
        personDataRepositoryImpl:LocalStorageRepositoryImpl
    ): LocalStorageRepository
}