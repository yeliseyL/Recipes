package com.eliseylobanov.recipes.network

import com.eliseylobanov.recipes.entities.Recipes
import retrofit2.http.GET
import retrofit2.http.Query

interface MealDBApiService {
    @GET("random.php")
    suspend fun getRandomRecipes(): Recipes

    @GET("search.php")
    suspend fun search(@Query("s") date: String): Recipes
}