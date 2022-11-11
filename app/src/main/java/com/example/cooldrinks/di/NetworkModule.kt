package com.example.cooldrinks.di

import com.example.cooldrinks.BuildConfig
import com.example.cooldrinks.remote.DrinksService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/"
    const val TimeOut = 50L

    @Provides
    @Singleton
    fun provideInterceptor(): Interceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    @Singleton
    fun provideHttpClient(interceptor: Interceptor) =
        OkHttpClient.Builder()
            .connectTimeout(TimeOut, TimeUnit.SECONDS) // connect timeout
            .writeTimeout(TimeOut, TimeUnit.SECONDS) // write timeout
            .readTimeout(TimeOut, TimeUnit.SECONDS) // read timeout
            .apply {
                if (BuildConfig.DEBUG) {
                    addInterceptor(interceptor)
                }
            }
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideDrinksRemoteService(retrofit: Retrofit): DrinksService = retrofit.create(DrinksService::class.java)
}