package com.gugugu.remote.response.meal

import com.google.gson.annotations.SerializedName

data class MealSchoolResponse(
    @field:SerializedName("schoolCode")
    val schoolCode: String,
    @field:SerializedName("schoolName")
    val schoolName: String,
    @field:SerializedName("local")
    val local: String
)