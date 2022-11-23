package com.example.cooldrinks.ui.drinkslist

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.cooldrinks.R
import com.example.cooldrinks.data.DrinksRepository
import com.example.cooldrinks.databinding.FragmentDrinksListBinding
import com.example.cooldrinks.model.Drink
import com.example.cooldrinks.ui.drinkslist.compose.DrinksList
import com.example.cooldrinks.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class DrinksListFragment : Fragment() {

    private val viewModel by viewModels<DrinksListViewModel>()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         return ComposeView(requireContext()).apply {
             setContent {
                 val drinksList by viewModel.drinks.observeAsState()
                 Scaffold() {
                     Column {
                         DrinksList(drinksList ?: emptyList())
                     }
                 }
             }
         }

    }
}