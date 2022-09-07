package com.example.cooldrinks.di

import com.example.cooldrinks.data.DefaultDrinksRepository
import com.example.cooldrinks.data.DrinksRepository
import com.example.cooldrinks.remote.DrinksService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideDrinksRepository(
        service: DrinksService
    ): DrinksRepository = DefaultDrinksRepository(service)
}