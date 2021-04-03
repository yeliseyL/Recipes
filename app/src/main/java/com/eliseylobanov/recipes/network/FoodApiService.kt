package com.eliseylobanov.recipes.network

import com.eliseylobanov.recipes.entities.Joke
import com.eliseylobanov.recipes.entities.Recipes
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodApiService {
    @GET("food/jokes/random")
    suspend fun getRandomJoke(@Query("apiKey") key: String): Joke
}