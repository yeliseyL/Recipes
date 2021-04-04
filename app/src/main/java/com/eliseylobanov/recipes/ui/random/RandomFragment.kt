package com.eliseylobanov.recipes.ui.random

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.eliseylobanov.recipes.ApiStatus
import com.eliseylobanov.recipes.R
import com.eliseylobanov.recipes.databinding.RandomFragmentBinding
import com.eliseylobanov.recipes.ui.MealsAdapter

class RandomFragment : Fragment(R.layout.random_fragment) {

    private val viewModel: RandomViewModel by lazy {
        val activity = requireNotNull(this.activity)
        ViewModelProvider(this, RandomViewModel.Factory(activity.application)).get(RandomViewModel::class.java)
    }

    lateinit var binding: RandomFragmentBinding
    lateinit var adapter: MealsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RandomFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        adapter = MealsAdapter(MealsAdapter.OnClickListener {
            viewModel.displayRecipeDetails(it)
        })

        binding.randomRecycler.adapter = adapter


        viewModel.status.observe(viewLifecycleOwner, {
            when (it) {
                ApiStatus.LOADING -> binding.progress.visibility = View.VISIBLE
                ApiStatus.DONE -> binding.progress.visibility = View.GONE
                ApiStatus.ERROR -> View.GONE
            }
        })

        viewModel.navigateToSelectedRecipe.observe(viewLifecycleOwner, {
            if (null != it) {
                this.findNavController().navigate(RandomFragmentDirections.actionRandomFragmentToDetailsFragment(it))
                viewModel.displayRecipeDetailsComplete()
            }
        })

        return binding.root
    }
}