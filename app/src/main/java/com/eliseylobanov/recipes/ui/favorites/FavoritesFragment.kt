package com.eliseylobanov.recipes.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.eliseylobanov.recipes.R
import com.eliseylobanov.recipes.databinding.FavoritesFragmentBinding
import com.eliseylobanov.recipes.ui.MealsAdapter

class FavoritesFragment : Fragment(R.layout.favorites_fragment) {

    private val viewModel: FavoritesViewModel by lazy {
        val activity = requireNotNull(this.activity)
        ViewModelProvider(this, FavoritesViewModel.Factory(activity.application)).get(FavoritesViewModel::class.java)
    }

    lateinit var binding: FavoritesFragmentBinding
    lateinit var adapter: MealsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FavoritesFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        adapter = MealsAdapter(MealsAdapter.OnClickListener {
            viewModel.displayRecipeDetails(it)
        })

        binding.favoritesRecycler.adapter = adapter

        viewModel.navigateToSelectedRecipe.observe(viewLifecycleOwner, {
            if (null != it) {
                this.findNavController().navigate(FavoritesFragmentDirections.actionFavoritesFragmentToDetailsFragment(it))
                viewModel.displayRecipeDetailsComplete()
            }
        })

        return binding.root
    }
}