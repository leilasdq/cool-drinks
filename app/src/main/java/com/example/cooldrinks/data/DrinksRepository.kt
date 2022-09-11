package com.example.cooldrinks.data

import com.example.cooldrinks.model.Drink
import com.example.cooldrinks.remote.DrinksService
import com.example.cooldrinks.utils.Resource
import com.example.cooldrinks.utils.networkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

interface DrinksRepository {
    fun getNonAlcoholicDrinks(): Flow<Resource<List<Drink>>>
}

class DefaultDrinksRepository @Inject constructor(
    private val drinksService: DrinksService
): DrinksRepository {

    override fun getNonAlcoholicDrinks(): Flow<Resource<List<Drink>>> =
        networkResult(
            { drinksService.getNonAlcoholicDrinks() },
            { data ->
                data.list.map { it.toDomain() }
            }
        )

}