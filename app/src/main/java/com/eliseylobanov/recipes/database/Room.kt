package com.eliseylobanov.recipes.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import com.eliseylobanov.recipes.entities.Meal

@Dao
interface MealDao {
    @Query("SELECT * FROM meal_table")
    fun getAllRecipes(): LiveData<List<DatabaseMeal>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(asteroids: List<DatabaseMeal>)

    @Update
    suspend fun update(meal: DatabaseMeal)

    @Query("SELECT * FROM meal_table WHERE isFavorite = 1")
    fun getAllFavorites(): LiveData<List<DatabaseMeal>>
}

@Database(entities = [DatabaseMeal::class], version = 1)
abstract class MealDatabase : RoomDatabase() {
    abstract val mealDao: MealDao
}

private lateinit var INSTANCE: MealDatabase

fun getDatabase(context: Context): MealDatabase {
    synchronized(MealDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                MealDatabase::class.java,
                "asteroids").build()
        }
    }
    return INSTANCE
}