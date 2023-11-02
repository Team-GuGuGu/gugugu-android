package com.gugugu.local.datasource

import android.util.Log
import com.gugugu.data.datasource.meal.MealCacheDataSource
import com.gugugu.domain.model.MealLocalSchool
import com.gugugu.local.dao.MealDao
import com.gugugu.local.entity.MealEntity
import com.gugugu.local.mapper.toModel
import java.lang.Exception
import java.lang.RuntimeException
import javax.inject.Inject

class MealCacheDataSourceImpl @Inject constructor(
    private val mealDao: MealDao
): MealCacheDataSource {
    override suspend fun getSchool(): MealLocalSchool {
        try {
            val data = mealDao.getSchool()
            Log.d("TAG", "getSchool: $data")
            return data.toModel()
        } catch (e: Exception) {
            Log.d("TAG", "getSchool: $e")
            throw RuntimeException("펑")
        }
//        Log.d("TAG", "getSchool: $data")
    }

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