package com.eliseylobanov.recipes.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.eliseylobanov.recipes.api.MealDBApi
import com.eliseylobanov.recipes.database.MealDatabase
import com.eliseylobanov.recipes.database.asDomainModel
import com.eliseylobanov.recipes.entities.Meal
import com.eliseylobanov.recipes.entities.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.UnknownHostException

class RecipeRepository(private val database: MealDatabase) {

    var recipes: MutableLiveData<ArrayList<Meal>> =
        Transformations.map(database.mealDao.getAllRecipes()) {
            it.asDomainModel()
        } as MutableLiveData<ArrayList<Meal>>

    suspend fun refreshRecipes() {
        withContext(Dispatchers.IO) {
            try {
                val mealList = mutableListOf<Meal>()
                for (i in 1..20) {
                    val result = MealDBApi.retrofitRecipesService.getRandomRecipes()
                    mealList.add(result.meals[0])
                }
                database.mealDao.insertAll(mealList.asDatabaseModel())
            } catch (ex: UnknownHostException) {
                Log.e("AsteroidRepository", "no network error")
            }
        }
    }
}