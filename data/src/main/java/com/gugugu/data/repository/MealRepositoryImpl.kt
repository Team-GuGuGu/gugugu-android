package com.gugugu.data.repository

import com.gugugu.data.BaseRepository
import com.gugugu.data.datasource.meal.MealCacheDataSource
import com.gugugu.data.datasource.meal.MealRemoteDataSource
import com.gugugu.domain.repository.MealRepository
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor(
    override val remote: MealRemoteDataSource,
    override val cache: MealCacheDataSource
): BaseRepository<MealRemoteDataSource, MealCacheDataSource>, MealRepository {
    override suspend fun getMeal(date: String) =
        cache.getSchool().let {
            remote.getMeal(
                local = it.local,
                school = it.school,
                date = date
            )
        }


    override suspend fun getSchool(school: String) =
        remote.getSchool(school)

    override suspend fun createSchool(local: String, schoolCode: String) =
        cache.createSchool(
            local = local,
            schoolCode = schoolCode
        )
}