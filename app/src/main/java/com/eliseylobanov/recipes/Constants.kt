package com.eliseylobanov.recipes

object Constants {
    const val RECIPES_BASE_URL = "https://www.themealdb.com/api/json/v1/1/"
    const val JOKE_BASE_URL = "https://api.spoonacular.com/"
}

enum class ApiStatus { LOADING, ERROR, DONE }