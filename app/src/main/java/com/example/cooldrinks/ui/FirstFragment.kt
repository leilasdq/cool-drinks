package com.example.cooldrinks.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.cooldrinks.R
import com.example.cooldrinks.data.DrinksRepository
import com.example.cooldrinks.databinding.FragmentFirstBinding
import com.example.cooldrinks.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class FirstFragment : Fragment() {

    @Inject
    lateinit var repo: DrinksRepository

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        lifecycleScope.launchWhenCreated {
            repo.getNonAlcoholicDrinks(this.coroutineContext).collect{ resource ->
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