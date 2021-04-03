package com.eliseylobanov.recipes.api

import com.eliseylobanov.recipes.Constants.RECIPES_BASE_URL
import com.eliseylobanov.recipes.network.MealDBApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MealDBApi {
    private val retrofitRecipes = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(RECIPES_BASE_URL)
            .build()

    val retrofitRecipesService: MealDBApiService = retrofitRecipes.create(MealDBApiService::class.java)
}