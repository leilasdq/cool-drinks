package com.example.cooldrinks.ui.drinkdetail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.cooldrinks.ui.drinkdetail.compose.DetailDrinkView
import com.example.cooldrinks.utils.compose.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DrinkDetailFragment : Fragment() {

    private val viewModel: DrinkDetailViewModel by viewModels()
    private val args: DrinkDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getDrinkItem(args.drinkId)
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                val detailDrink by viewModel.drinkDetail.observeAsState()

                AppTheme {
                    Scaffold() {
                        detailDrink?.let {
                            DetailDrinkView(
                                item = it,
                                onBackButtonClicked = ::onNavigateUp
                            )
                        }
                    }
                }
            }
        }
    }

    private fun onNavigateUp() = findNavController().navigateUp()
}