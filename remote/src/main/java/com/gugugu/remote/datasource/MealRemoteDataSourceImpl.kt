package com.gugugu.remote.datasource

import com.gugugu.data.datasource.meal.MealRemoteDataSource
import com.gugugu.remote.mapper.toModel
import com.gugugu.remote.mapper.toModels
import com.gugugu.remote.service.MealService
import com.gugugu.remote.util.guguguApiCall
import javax.inject.Inject

class MealRemoteDataSourceImpl @Inject constructor(
    private val mealService: MealService
): MealRemoteDataSource {
    override suspend fun getMeal(local: String, school: String, date: String) = guguguApiCall {
        mealService.get(local, school, date).data.toModels()
    }

    override suspend fun getSchool(local: String, school: String) = guguguApiCall {
        mealService.school(local, school).data.toModels()
    }

}