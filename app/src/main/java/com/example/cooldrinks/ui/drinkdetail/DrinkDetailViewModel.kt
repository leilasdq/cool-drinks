package com.example.cooldrinks.ui.drinkdetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cooldrinks.data.DrinksRepository
import com.example.cooldrinks.model.Drink
import com.example.cooldrinks.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrinkDetailViewModel @Inject constructor(
    private val drinkRepo: DrinksRepository
): ViewModel() {

    private val _drinkDetailData = MutableLiveData<Drink>()
    val drinkDetail: LiveData<Drink> = _drinkDetailData

    fun getDrinkItem(drinkId: Int) {
        viewModelScope.launch {
            drinkRepo.getDrinkDetail(drinkId).collect { result ->
                when(result) {
                    is Resource.Success -> {
                        result.data.let {
                            _drinkDetailData.value = it
                            Log.d("drinksss", "getDrinkItem: ${result.data.name}")
                        }
                    }
                    is Resource.Error -> {
                        Log.d("drinksss", "getDrinkItem: ${result.error.message}")
                    }
                    else -> {}
                }
            }
        }
    }
}