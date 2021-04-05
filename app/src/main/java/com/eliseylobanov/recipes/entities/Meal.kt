package com.eliseylobanov.recipes.entities

import android.os.Parcelable
import com.eliseylobanov.recipes.database.DatabaseMeal
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Meal(
    val idMeal: String,
    val strArea: String,
    val strCategory: String,
    val strIngredient1: String?,
    val strIngredient10: String?,
    val strIngredient11: String?,
    val strIngredient12: String?,
    val strIngredient13: String?,
    val strIngredient14: String?,
    val strIngredient15: String?,
    val strIngredient16: String?,
    val strIngredient17: String?,
    val strIngredient18: String?,
    val strIngredient19: String?,
    val strIngredient2: String?,
    val strIngredient20: String?,
    val strIngredient3: String?,
    val strIngredient4: String?,
    val strIngredient5: String?,
    val strIngredient6: String?,
    val strIngredient7: String?,
    val strIngredient8: String?,
    val strIngredient9: String?,
    val strInstructions: String?,
    val strMeal: String,
    val strMealThumb: String,
    val strMeasure1: String?,
    val strMeasure10: String?,
    val strMeasure11: String?,
    val strMeasure12: String?,
    val strMeasure13: String?,
    val strMeasure14: String?,
    val strMeasure15: String?,
    val strMeasure16: String?,
    val strMeasure17: String?,
    val strMeasure18: String?,
    val strMeasure19: String?,
    val strMeasure2: String?,
    val strMeasure20: String?,
    val strMeasure3: String?,
    val strMeasure4: String?,
    val strMeasure5: String?,
    val strMeasure6: String?,
    val strMeasure7: String?,
    val strMeasure8: String?,
    val strMeasure9: String?,
    var isFavorite: Boolean
) : Parcelable {
    fun getIngredients() =
        ("$strIngredient1: $strMeasure1\n" +
        "$strIngredient2: $strMeasure2\n" +
        "$strIngredient3: $strMeasure3\n" +
        "$strIngredient4: $strMeasure4\n" +
        "$strIngredient5: $strMeasure5\n" +
        "$strIngredient6: $strMeasure6\n" +
        "$strIngredient7: $strMeasure7\n" +
        "$strIngredient8: $strMeasure8\n" +
        "$strIngredient9: $strMeasure9\n" +
        "$strIngredient10: $strMeasure10\n" +
        "$strIngredient11: $strMeasure11\n" +
        "$strIngredient12: $strMeasure12\n" +
        "$strIngredient13: $strMeasure13\n" +
        "$strIngredient14: $strMeasure14\n" +
        "$strIngredient15: $strMeasure15\n" +
        "$strIngredient16: $strMeasure16\n" +
        "$strIngredient17: $strMeasure17\n" +
        "$strIngredient18: $strMeasure18\n" +
        "$strIngredient19: $strMeasure19\n" +
        "$strIngredient20: $strMeasure20\n").trimMargin(":").trim()
}

fun List<Meal>.asDatabaseModel(): List<DatabaseMeal> {
    return map {
        DatabaseMeal(
            idMeal = it.idMeal,
            strArea = it.strArea,
            strCategory = it.strCategory,
            strIngredient1 = it.strIngredient1,
            strIngredient10 = it.strIngredient10,
            strIngredient11 = it.strIngredient11,
            strIngredient12 = it.strIngredient12,
            strIngredient13 = it.strIngredient13,
            strIngredient14 = it.strIngredient14,
            strIngredient15 = it.strIngredient15,
            strIngredient16 = it.strIngredient16,
            strIngredient17 = it.strIngredient17,
            strIngredient18 = it.strIngredient18,
            strIngredient19 = it.strIngredient19,
            strIngredient20 = it.strIngredient20,
            strIngredient2 = it.strIngredient2,
            strIngredient3 = it.strIngredient3,
            strIngredient4 = it.strIngredient4,
            strIngredient5 = it.strIngredient5,
            strIngredient6 = it.strIngredient6,
            strIngredient7 = it.strIngredient7,
            strIngredient8 = it.strIngredient8,
            strIngredient9 = it.strIngredient9,
            strInstructions = it.strInstructions,
            strMeal = it.strMeal,
            strMealThumb = it.strMealThumb,
            strMeasure1 = it.strMeasure1,
            strMeasure2 = it.strMeasure2,
            strMeasure3 = it.strMeasure3,
            strMeasure4 = it.strMeasure4,
            strMeasure5 = it.strMeasure5,
            strMeasure6 = it.strMeasure6,
            strMeasure7 = it.strMeasure7,
            strMeasure8 = it.strMeasure8,
            strMeasure9 = it.strMeasure9,
            strMeasure10 = it.strMeasure10,
            strMeasure11 = it.strMeasure11,
            strMeasure12 = it.strMeasure12,
            strMeasure13 = it.strMeasure13,
            strMeasure14 = it.strMeasure14,
            strMeasure15 = it.strMeasure15,
            strMeasure16 = it.strMeasure16,
            strMeasure17 = it.strMeasure17,
            strMeasure18 = it.strMeasure18,
            strMeasure19 = it.strMeasure19,
            strMeasure20 = it.strMeasure20,
            isFavorite = it.isFavorite)
    }
}