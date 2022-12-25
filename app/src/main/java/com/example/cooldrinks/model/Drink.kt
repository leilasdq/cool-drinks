package com.example.cooldrinks.model

data class Drink(
    val name: String,
    val imageUrl: String,
    val id: String,
    val instruction: String?,
    val ingredient: List<String>?
)
