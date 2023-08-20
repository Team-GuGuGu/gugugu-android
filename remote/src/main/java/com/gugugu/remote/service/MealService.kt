package com.gugugu.remote.service

import com.gugugu.remote.response.BaseResponse
import com.gugugu.remote.response.meal.MealGetResponse
import com.gugugu.remote.response.meal.MealSchoolResponse
import com.gugugu.remote.util.GuguguUrl
import retrofit2.http.GET
import retrofit2.http.Query

interface MealService {

    @GET(GuguguUrl.Meal.GET)
    suspend fun get(
        @Query("local") local: String,
        @Query("schoolCode") school: String,
        @Query("date") date: String
    ): BaseResponse<List<MealGetResponse>>

    @GET(GuguguUrl.Meal.SCHOOL)
    suspend fun school(
        @Query("local") local: String,
        @Query("school") school: String
    ): BaseResponse<List<MealSchoolResponse>>
}