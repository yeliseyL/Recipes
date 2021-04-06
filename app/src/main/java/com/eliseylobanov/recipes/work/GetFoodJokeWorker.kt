package com.eliseylobanov.recipes.work

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.graphics.Color.RED
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.DEFAULT_ALL
import androidx.core.app.NotificationCompat.PRIORITY_MAX
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.eliseylobanov.recipes.BuildConfig
import com.eliseylobanov.recipes.R
import com.eliseylobanov.recipes.api.FoodApi
import com.eliseylobanov.recipes.entities.Joke
import retrofit2.HttpException

class GetFoodJokeWorker(appContext: Context, params: WorkerParameters):
    CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "GetFoodJokeWorker"
        const val NOTIFICATION_ID = 0
        const val NOTIFICATION_NAME = "Recipes"
        const val NOTIFICATION_CHANNEL = "Recipes_channel_01"
    }

    override suspend fun doWork(): Result {
        return try {
            val joke = FoodApi.retrofitJokeService.getRandomJoke(BuildConfig.JOKE_API_KEY)
            sendNotification(joke)
            Result.success()
        } catch (e: HttpException) {
            Result.retry()
        }
    }

    private fun sendNotification(joke: Joke) {
        val notificationManager =
            applicationContext.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val titleNotification = applicationContext.getString(R.string.notification_title)
        val notification = NotificationCompat.Builder(applicationContext, NOTIFICATION_CHANNEL)
            .setSmallIcon(R.drawable.ic_recipes_random)
            .setContentTitle(titleNotification)
            .setContentText(joke.text)
            .setStyle(NotificationCompat.BigTextStyle())
            .setDefaults(DEFAULT_ALL)
            .setAutoCancel(true)
            .setPriority(PRIORITY_MAX)
            .setChannelId(NOTIFICATION_CHANNEL)

            val channel =
                NotificationChannel(NOTIFICATION_CHANNEL, NOTIFICATION_NAME, NotificationManager.IMPORTANCE_HIGH)
            channel.enableLights(true)
            channel.lightColor = RED
            notificationManager.createNotificationChannel(channel)

        notificationManager.notify(NOTIFICATION_ID, notification.build())
    }
}