package com.example.cooldrinks.ui.drinkslist

import SingleEvent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cooldrinks.data.DrinksRepository
import com.example.cooldrinks.model.Drink
import com.example.cooldrinks.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrinksListViewModel @Inject constructor(
    private val drinksRepo: DrinksRepository
): ViewModel() {

    private val _drinksListData = MutableLiveData<List<Drink>>()
    val drinks: LiveData<List<Drink>> = _drinksListData
    private val _drinksErrorData = MutableLiveData<SingleEvent<Throwable>>()
    val drinkError: LiveData<SingleEvent<Throwable>> = _drinksErrorData

    init {
        getDrinks()
    }

    private fun getDrinks() {
        viewModelScope.launch(Dispatchers.IO) {
            drinksRepo.getNonAlcoholicDrinks().collect { resource ->
                when(resource) {
                    is Resource.Success -> {
                        resource.data.let {
                            _drinksListData.postValue(it)
                        }
                        Log.d("testtt", "onViewCreated: success ${resource.data}")
                    }
                    is Resource.Error -> {
                        _drinksErrorData.value = SingleEvent(resource.error)
                    }
                    is Resource.Loading -> {
                        Log.d("testtt", "onViewCreated: loading")
                    }
                }
            }
        }
    }

}