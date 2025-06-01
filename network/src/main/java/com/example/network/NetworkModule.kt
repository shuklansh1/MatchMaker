package com.example.network

import com.example.data.ApiClient
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providesApiClient(
    ): ApiClient = Retrofit.Builder()
        .baseUrl("https://randomuser.me/")
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            ).asLenient()
        )
        .client(
            OkHttpClient.Builder()
                .build()
        )
        .build()
        .create(ApiClient::class.java)
}