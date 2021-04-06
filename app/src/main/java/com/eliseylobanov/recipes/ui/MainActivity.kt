package com.eliseylobanov.recipes.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.eliseylobanov.recipes.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpNavigation()
        setSupportActionBar(toolbar)
    }

    private fun setUpNavigation() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)
        val navController = findNavController(R.id.nav_host_fragment)
        bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener{ _, destination, _ ->
            title = when (destination.id) {
                R.id.randomFragment -> getString(R.string.random_fragment_label)
                R.id.searchFragment -> getString(R.string.search_fragment_label)
                R.id.favoritesFragment -> getString(R.string.favorites_fragment_label)
                R.id.detailsFragment -> getString(R.string.details_fragment_label)
                else -> getString(R.string.default_label)
            }
        }
    }
}