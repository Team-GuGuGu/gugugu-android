package com.gugugu.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gugugu.local.util.GuguguTable


@Entity(
    tableName = GuguguTable.Meal
)
data class MealEntity(
    @PrimaryKey val idx: Int = 0,
    val local: String,
    val school: String
)
