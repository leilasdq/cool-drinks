package com.example.cooldrinks.data

import com.example.cooldrinks.model.Drink
import com.example.cooldrinks.remote.dtos.DrinksDto

fun DrinksDto.toDomain() = Drink(
    name, imageUrl, id, instruction,
    ingredientAndMeasure = ingredient.zip(measure).toMap()
)