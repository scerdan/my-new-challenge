package com.mynewchallenge.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.mynewchallenge.data.service.GithubApiService
import com.mynewchallenge.domain.repository.TokenRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideTokenRepository(dataStore: DataStore<Preferences>): TokenRepository {
        return TokenRepository(dataStore)
    }

    @Provides
    @Singleton
    fun provideAuthInterceptor(tokenRepository: TokenRepository): Interceptor {
        return Interceptor { chain ->
            val originalRequest = chain.request()
            val token = runBlocking { tokenRepository.token.first() }
            val newRequest = originalRequest.newBuilder()
                .header("Authorization", "Bearer $token")
                .build()

            chain.proceed(newRequest)
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(authInterceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): GithubApiService =
        Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GithubApiService::class.java)
}

