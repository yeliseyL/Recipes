package com.eliseylobanov.recipes.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.eliseylobanov.recipes.R
import com.eliseylobanov.recipes.databinding.DetailsFragmentBinding

class DetailsFragment : Fragment(R.layout.details_fragment) {

    private val viewModel: DetailsViewModel by lazy {
        ViewModelProviders.of(this).get(DetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DetailsFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val meal = DetailsFragmentArgs.fromBundle(requireArguments()).selectedRecipe

        binding.meal = meal

        return binding.root
    }
}