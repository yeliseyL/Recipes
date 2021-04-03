package com.eliseylobanov.recipes.network

import com.eliseylobanov.recipes.entities.Recipes
import retrofit2.http.GET
import retrofit2.http.Query

interface MealDBApiService {
    @GET("random.php")
    suspend fun getRandomRecipes(): Recipes

//    @GET("planetary/apod")
//    suspend fun getPictureOfTheDay(@Query("date") date: String,
//                                   @Query("api_key") key: String): PictureOfDay
//
//    @GET("EPIC/api/natural/images")
//    suspend fun getEarth(@Query("api_key") key: String): List<Earth>
//
//    @GET("mars-photos/api/v1/rovers/curiosity/photos")
//    suspend fun getMars(@Query("earth_date") date: String,
//                        @Query("api_key") key: String): Mars
//
//    @GET("DONKI/notifications")
//    suspend fun getWeather(@Query("startDate") startDate: String,
//                           @Query("endDate") endDate: String,
//                           @Query("type") type: String,
//                           @Query("api_key") key: String): Weather
}