package com.eliseylobanov.recipes.ui.favorites

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.eliseylobanov.recipes.ApiStatus
import com.eliseylobanov.recipes.database.getDatabase
import com.eliseylobanov.recipes.entities.Meal
import com.eliseylobanov.recipes.repository.RecipeRepository
import com.eliseylobanov.recipes.ui.random.RandomViewModel
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class FavoritesViewModel(application: Application) : ViewModel() {
    private val _navigateToSelectedRecipe = MutableLiveData<Meal?>()
    val navigateToSelectedRecipe: LiveData<Meal?>
        get() = _navigateToSelectedRecipe

    private val database = getDatabase(application)
    private val recipeRepository = RecipeRepository(database)

    val favorites = recipeRepository.favorites

    fun displayRecipeDetails(meal: Meal) {
        _navigateToSelectedRecipe.value = meal
    }

    fun displayRecipeDetailsComplete() {
        _navigateToSelectedRecipe.value = null
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FavoritesViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return FavoritesViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}