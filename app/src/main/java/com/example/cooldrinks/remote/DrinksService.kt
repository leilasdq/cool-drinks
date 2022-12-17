package com.example.cooldrinks.remote

import com.example.cooldrinks.remote.dtos.DrinksResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface DrinksService {

    @GET("filter.php?a=Non_Alcoholic")
    suspend fun getNonAlcoholicDrinks(): DrinksResponseDto

    @GET("lookup.php")
    suspend fun getDrinkDetail(
        @Query("i") id: Int
    ): DrinksResponseDto
}