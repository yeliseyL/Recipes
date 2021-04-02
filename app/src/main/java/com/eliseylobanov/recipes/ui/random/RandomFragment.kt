package com.eliseylobanov.recipes.ui.random

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.eliseylobanov.recipes.R

class RandomFragment : Fragment(R.layout.random_fragment) {

    private val viewModel: RandomViewModel by lazy {
        ViewModelProviders.of(this).get(RandomViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.random_fragment, container, false)
    }
}