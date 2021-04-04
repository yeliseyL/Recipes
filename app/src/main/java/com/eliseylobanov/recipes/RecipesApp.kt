package com.eliseylobanov.recipes

import android.app.Application
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.eliseylobanov.recipes.work.GetFoodJokeWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class RecipesApp : Application() {

    val applicationScope = CoroutineScope(Dispatchers.Default)

    private fun delayedInit() {
        applicationScope.launch {
            setupRecurringWork()
        }
    }

    private fun setupRecurringWork() {
//        val constraints = Constraints.Builder()
//            .setRequiredNetworkType(NetworkType.UNMETERED)
//            .setRequiresBatteryNotLow(true)
//            .setRequiresCharging(true)
//            .apply {
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                    setRequiresDeviceIdle(true)
//                }
//            }.build()

        val repeatingRequest
                = PeriodicWorkRequestBuilder<GetFoodJokeWorker>(1, TimeUnit.DAYS)
//            .setConstraints(constraints)
            .build()

        WorkManager.getInstance().enqueueUniquePeriodicWork(
            GetFoodJokeWorker.WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            repeatingRequest)
    }

    override fun onCreate() {
        super.onCreate()
        delayedInit()
    }
}