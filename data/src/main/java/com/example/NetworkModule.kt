package com.example

import com.example.data.ApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun providesLearnApiClient(
        httpClient: OkHttpClient,
    ): ApiClient = Retrofit.Builder()
        .baseUrl("https://randomuser.me/")
        .client(httpClient)
        .build()
        .create(ApiClient::class.java)
}
