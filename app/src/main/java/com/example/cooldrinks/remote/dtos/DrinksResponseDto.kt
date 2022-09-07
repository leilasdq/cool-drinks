package com.example.cooldrinks.remote.dtos

import com.google.gson.annotations.SerializedName

data class DrinksResponseDto(
    @SerializedName("drinks")
    val list: List<DrinksDto>
)
