package com.eliseylobanov.recipes.ui.details

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.eliseylobanov.recipes.database.getDatabase
import com.eliseylobanov.recipes.entities.Meal
import com.eliseylobanov.recipes.entities.asDatabaseModel
import com.eliseylobanov.recipes.repository.RecipeRepository
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class DetailsViewModel(application: Application, var meal: Meal) : ViewModel() {
    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean>
        get() = _isFavorite

    private val database = getDatabase(application)
    private val recipeRepository = RecipeRepository(database)

    init {
        _isFavorite.value = meal.isFavorite
    }

    fun toggleFavorites() {
        meal.isFavorite = !meal.isFavorite
        _isFavorite.value = meal.isFavorite
        val listWrapper = listOf(meal)
        viewModelScope.launch {
            try {
                recipeRepository.update(listWrapper.asDatabaseModel()[0])
            } catch (ex: UnknownHostException) {
                Log.e("Recipes", "Network error")
            }
        }
    }

    class Factory(val app: Application, val meal: Meal) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return DetailsViewModel(app, meal) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}