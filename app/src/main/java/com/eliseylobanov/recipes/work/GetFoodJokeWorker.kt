package com.eliseylobanov.recipes.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.eliseylobanov.recipes.database.getDatabase
import com.eliseylobanov.recipes.repository.RecipeRepository
import retrofit2.HttpException

class GetFoodJokeWorker(appContext: Context, params: WorkerParameters):
    CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "GetFoodJokeWorker"
    }

    override suspend fun doWork(): Result {
        val database = getDatabase(applicationContext)
        val repository = RecipeRepository(database)
        return try {
            repository.refreshRecipes()
            Result.success()
        } catch (e: HttpException) {
            Result.retry()
        }
    }
}