package com.gugugu.remote.response.meal

import com.google.gson.annotations.SerializedName

data class MealGetResponse(
    @field:SerializedName("allergy")
    val allergy: String,
    @field:SerializedName("calorie")
    val calorie: String,
    @field:SerializedName("menu")
    val menu: String,
    @field:SerializedName("time")
    val time: String
)