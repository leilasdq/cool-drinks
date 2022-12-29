package com.example.cooldrinks.data

import com.example.cooldrinks.model.Drink
import com.example.cooldrinks.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeDrinksRepository(): DrinksRepository {
    private var shouldShowError = false

    fun changeShowErrorState(value: Boolean) {
        shouldShowError = value
    }

    override fun getNonAlcoholicDrinks(): Flow<Resource<List<Drink>>> {
        return if (shouldShowError) {
            flowOf(
                Resource.Error(Throwable("Error"))
            )
        } else {
            flowOf(
                Resource.Success(
                    listOf(Drink("test name", "test url", "1", null, mapOf()))
                )
            )
        }
    }

    override fun getDrinkDetail(id: Int): Flow<Resource<Drink>> {
        return if (shouldShowError) {
            flowOf(
                Resource.Error(Throwable("Error"))
            )
        } else {
            flowOf(
                Resource.Success(
                    Drink(
                        "test name", "", "1", null, mapOf()
                    )
                )
            )
        }
    }
}