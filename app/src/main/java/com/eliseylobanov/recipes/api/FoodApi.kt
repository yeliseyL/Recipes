package com.eliseylobanov.recipes.api

import com.eliseylobanov.recipes.Constants
import com.eliseylobanov.recipes.network.FoodApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object FoodApi {
    private val retrofitJoke = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Constants.JOKE_BASE_URL)
        .build()

    val retrofitJokeService: FoodApiService = retrofitJoke.create(FoodApiService::class.java)
}