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
import androidx.compose.ui.platform.ComposeView
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

    @Inject
    lateinit var repo: DrinksRepository

    private var _binding: FragmentDrinksListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         return ComposeView(requireContext()).apply {
             setContent {
                 Scaffold() {
                     Column {
                         val fakeList = mutableListOf<Drink>()
                         for (i in 0 .. 5) {
                             fakeList.add(Drink("name $i", "https://www.thecocktaildb.com/images/media/drink/xwqvur1468876473.jpg", "$i"))
                         }
                         DrinksList(fakeList)
                     }
                 }
             }
         }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }*/

        lifecycleScope.launchWhenCreated {
            repo.getNonAlcoholicDrinks().collect{ resource ->
                when(resource) {
                    is Resource.Success -> {
                        Log.d("testtt", "onViewCreated: success ${resource.data}")
                    }
                    is Resource.Error -> {
                        Log.d("testtt", "onViewCreated: error ${resource.error.message}")
                    }
                    is Resource.Loading -> {
                        Log.d("testtt", "onViewCreated: loading")
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}