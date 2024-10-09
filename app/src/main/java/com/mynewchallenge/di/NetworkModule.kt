package com.mynewchallenge.di

import com.mynewchallenge.data.service.GithubApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private fun getToken(): String {
        // Supongamos que aquí recuperas el token almacenado
        // Puedes reemplazar esto con SharedPreferences o cualquier método para obtener el token
        return ""
    }

    @Provides
    @Singleton
    fun provideAuthInterceptor(): Interceptor {
        return Interceptor { chain ->
            val originalRequest = chain.request()

            // Agregamos el encabezado Authorization solo si hay un token disponible
            val token = getToken()
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
            .addInterceptor(authInterceptor) // Agregamos el interceptor aquí
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): GithubApiService =
        Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(okHttpClient) // Usamos el OkHttpClient con el interceptor
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GithubApiService::class.java)
}

