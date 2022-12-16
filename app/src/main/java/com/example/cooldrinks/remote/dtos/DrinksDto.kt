package com.example.cooldrinks.remote.dtos

import com.google.gson.annotations.SerializedName

data class DrinksDto(
    @SerializedName("strDrink")
    val name: String,
    @SerializedName("strDrinkThumb")
    val imageUrl: String,
    @SerializedName("idDrink")
    val id: String,
    @SerializedName("strInstructions")
    val instruction: String?
)