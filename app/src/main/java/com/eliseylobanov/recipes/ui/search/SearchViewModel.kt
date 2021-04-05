package com.eliseylobanov.recipes.ui.search

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

class SearchViewModel(application: Application) : AndroidViewModel(application) {
    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    private val _navigateToSelectedRecipe = MutableLiveData<Meal?>()
    val navigateToSelectedRecipe: LiveData<Meal?>
        get() = _navigateToSelectedRecipe

    private val database = getDatabase(application)
    private val recipeRepository = RecipeRepository(database)

    val searchResults = recipeRepository.searchResults

    fun searchRecipes(name: String) {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                recipeRepository.searchRecipes(name)
                _status.value = ApiStatus.DONE
            } catch (ex: UnknownHostException) {
                Log.e("Recipes", "Network error")
                _status.value = ApiStatus.ERROR
            }
        }
    }

    fun displayRecipeDetails(meal: Meal) {
        _navigateToSelectedRecipe.value = meal
    }

    fun displayRecipeDetailsComplete() {
        _navigateToSelectedRecipe.value = null
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return SearchViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}