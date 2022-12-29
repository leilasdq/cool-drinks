package com.example.cooldrinks.data

import com.example.cooldrinks.model.Drink
import com.example.cooldrinks.remote.DrinksService
import com.example.cooldrinks.utils.Resource
import com.example.cooldrinks.utils.networkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface DrinksRepository {
    fun getNonAlcoholicDrinks(): Flow<Resource<List<Drink>>>
    fun getDrinkDetail(id: Int): Flow<Resource<Drink>>
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

    override fun getDrinkDetail(id: Int): Flow<Resource<Drink>> =
        networkResult(
            { drinksService.getDrinkDetail(id) },
            { data ->
                data.list[0].toDomain()
            }
        )

}