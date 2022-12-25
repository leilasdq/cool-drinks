package com.example.cooldrinks.di

import com.example.cooldrinks.BuildConfig
import com.example.cooldrinks.remote.DrinksService
import com.example.cooldrinks.remote.dtos.DrinkIngredientListDeserialize
import com.example.cooldrinks.remote.dtos.DrinksDto
import com.example.cooldrinks.utils.BASE_URL
import com.example.cooldrinks.utils.TimeOut
import com.google.gson.Gson
import com.google.gson.GsonBuilder
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
    fun provideGson() = GsonBuilder()
        .registerTypeAdapter(DrinksDto::class.java, DrinkIngredientListDeserialize)
        .create()

    @Singleton
    @Provides
    fun provideGsonConverter(gson: Gson) = GsonConverterFactory.create(gson)

    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient,
        factory: GsonConverterFactory
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(factory)
            .build()

    @Provides
    @Singleton
    fun provideDrinksRemoteService(retrofit: Retrofit): DrinksService = retrofit.create(DrinksService::class.java)
}