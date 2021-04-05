package com.eliseylobanov.recipes.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.eliseylobanov.recipes.ApiStatus
import com.eliseylobanov.recipes.R
import com.eliseylobanov.recipes.databinding.RandomFragmentBinding
import com.eliseylobanov.recipes.databinding.SearchFragmentBinding
import com.eliseylobanov.recipes.ui.MealsAdapter
import com.eliseylobanov.recipes.ui.random.RandomFragmentDirections
import com.eliseylobanov.recipes.ui.random.RandomViewModel

class SearchFragment : Fragment(R.layout.search_fragment) {

    lateinit var binding: SearchFragmentBinding
    lateinit var adapter: MealsAdapter

    private val viewModel: SearchViewModel by lazy {
        val activity = requireNotNull(this.activity)
        ViewModelProvider(this,
            SearchViewModel.Factory(activity.application)
        ).get(SearchViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SearchFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        adapter = MealsAdapter(MealsAdapter.OnClickListener {
            viewModel.displayRecipeDetails(it)
        })

        binding.searchRecycler.adapter = adapter

        binding.inputLayout.setEndIconOnClickListener {
            viewModel.searchRecipes(binding.inputEditText.text.toString())
        }


        viewModel.status.observe(viewLifecycleOwner, {
            when (it) {
                ApiStatus.LOADING -> {
                    binding.progress.visibility = View.VISIBLE
                }
                ApiStatus.DONE -> {
                    binding.progress.visibility = View.GONE
                }
                ApiStatus.ERROR -> {
                    binding.progress.visibility = View.VISIBLE
                }
            }
        })

        viewModel.navigateToSelectedRecipe.observe(viewLifecycleOwner, {
            if (null != it) {
                this.findNavController()
                    .navigate(SearchFragmentDirections.actionSearchFragmentToDetailsFragment(it))
                viewModel.displayRecipeDetailsComplete()
            }
        })

        return binding.root
    }
}