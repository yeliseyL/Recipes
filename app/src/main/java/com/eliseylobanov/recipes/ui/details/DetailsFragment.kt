package com.eliseylobanov.recipes.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.eliseylobanov.recipes.R
import com.eliseylobanov.recipes.databinding.DetailsFragmentBinding


class DetailsFragment : Fragment(R.layout.details_fragment) {

    lateinit var viewModel: DetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DetailsFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val meal = DetailsFragmentArgs.fromBundle(requireArguments()).selectedRecipe
        val activity = requireNotNull(this.activity)
        viewModel = ViewModelProvider(this, DetailsViewModel.Factory(activity.application, meal))
                    .get(DetailsViewModel::class.java)
        binding.meal = viewModel.meal

        viewModel.isFavorite.observe(viewLifecycleOwner, {
           if (it) {binding.like.setImageResource(R.drawable.ic_favorite_solid) }
           else {binding.like.setImageResource(R.drawable.ic_favorite)}
            binding.like.invalidate()
        })

        binding.like.setOnClickListener {
            viewModel.toggleFavorites()
        }

        return binding.root
    }
}