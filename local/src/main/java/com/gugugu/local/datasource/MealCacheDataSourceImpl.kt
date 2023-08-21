package com.gugugu.local.datasource

import com.gugugu.data.datasource.meal.MealCacheDataSource
import com.gugugu.local.dao.MealDao
import com.gugugu.local.entity.MealEntity
import com.gugugu.local.mapper.toModel
import javax.inject.Inject

class MealCacheDataSourceImpl @Inject constructor(
    private val mealDao: MealDao
): MealCacheDataSource {
    override suspend fun getSchool() =
        mealDao.getSchool().toModel()

    override suspend fun deleteSchool() =
        mealDao.deleteSchool()

    override suspend fun createSchool(local: String, school: String) =
        mealDao.insertSchool(
            MealEntity(
                local = local,
                school = school
            )
        )

}