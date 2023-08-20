package com.gugugu.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gugugu.local.dao.MealDao
import com.gugugu.local.entity.MealEntity


@Database(
    entities = [
        MealEntity::class
   ],
    version = 1
)
abstract class GuguguDataBase: RoomDatabase() {
    abstract fun mealDao(): MealDao
}