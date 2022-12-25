package com.example.cooldrinks.remote.dtos

import com.google.gson.*
import java.lang.Exception
import java.lang.reflect.Type

data class DrinksDto(
    val name: String,
    val imageUrl: String,
    val id: String,
    val instruction: String?,
    val ingredient: List<IngredientDto>
)

data class IngredientDto(
    val ingredientTitle: String
)

object DrinkIngredientListDeserialize: JsonDeserializer<DrinksDto> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): DrinksDto? {
        if (json == null || json.isJsonNull)
            return null

        val ingredientList = mutableListOf<IngredientDto>()
        val keys = (json as JsonObject).keySet()
        try {
            val name = (json as JsonObject).get("strDrink").asString
            val image = (json as JsonObject).get("strDrinkThumb").asString
            val id = (json as JsonObject).get("idDrink").asString
            val instruction = if ((json as JsonObject).get("strInstructions") != null)
                (json as JsonObject).get("strInstructions").asString
            else null

            for (items in keys) {
                val jsonItem = (json as JsonObject).get(items)
                if (items.startsWith("strIngredient") && jsonItem.isJsonNull.not())
                    ingredientList.add(IngredientDto(jsonItem.asString))
            }


            return DrinksDto(name, image, id, instruction, ingredientList)
        } catch (e: Exception) {
            throw JsonParseException("The api was not in the expected format.")
        }

    }

}