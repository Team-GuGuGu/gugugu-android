package com.gugugu.data.datasource.meal

import com.gugugu.domain.model.MealLocalSchool

interface MealCacheDataSource {

    suspend fun getSchool(): MealLocalSchool

    suspend fun createSchool(
        local: String,
        schoolCode: String
    )
}