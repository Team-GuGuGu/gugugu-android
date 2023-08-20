package com.gugugu.data.datasource.meal

import com.gugugu.domain.model.MealGet
import com.gugugu.domain.model.MealSearchSchool

interface MealRemoteDataSource {
    suspend fun getMeal(
        local: String,
        school: String,
        date: String
    ): List<MealGet>

    suspend fun getSchool(
        school: String
    ): List<MealSearchSchool>
}