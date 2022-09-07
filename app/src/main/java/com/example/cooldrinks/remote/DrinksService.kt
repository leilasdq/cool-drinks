package com.example.cooldrinks.remote

import com.example.cooldrinks.remote.dtos.DrinksResponseDto
import retrofit2.http.GET

interface DrinksService {

    @GET("filter.php?a=Non_Alcoholic")
    suspend fun getNonAlcoholicDrinks(): DrinksResponseDto
}