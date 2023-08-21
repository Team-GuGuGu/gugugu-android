package com.gugugu.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.gugugu.local.entity.MealEntity
import com.gugugu.local.util.GuguguTable


@Dao
interface MealDao {

    @Query("SELECT * FROM ${GuguguTable.Meal} where idx = 0")
    suspend fun getSchool(): MealEntity

    @Insert
    suspend fun insertSchool(entity: MealEntity)

    @Query("DELETE FROM ${GuguguTable.Meal}")
    suspend fun deleteSchool()

}