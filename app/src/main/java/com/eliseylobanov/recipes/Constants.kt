package com.eliseylobanov.recipes

object Constants {
    const val RECIPES_BASE_URL = "https://www.themealdb.com/api/json/v1/1/"
    const val SEED = 55
}

enum class ApiStatus { LOADING, ERROR, DONE }