package com.gugugu.domain.repository

import com.gugugu.domain.model.MealGet
import com.gugugu.domain.model.MealSearchSchool

interface MealRepository {
    suspend fun getMeal(
        date: String
    ): List<MealGet>

    suspend fun getSchool(
        school: String
    ): List<MealSearchSchool>

    suspend fun createSchool(
        local: String,
        schoolCode: String
    )
}