package com.gugugu.remote.mapper


import com.gugugu.domain.model.MealGet
import com.gugugu.domain.model.MealSearchSchool
import com.gugugu.remote.response.meal.MealGetResponse
import com.gugugu.remote.response.meal.MealSchoolResponse

internal fun MealSchoolResponse.toModel() =
    MealSearchSchool(
        schoolName = schoolName,
        schoolCode = schoolCode,
        local = local
    )

@JvmName("MealSchoolResponseMapper")
internal fun List<MealSchoolResponse>.toModels() =
    this.map {
        it.toModel()
    }

internal fun MealGetResponse.toModel() =
    MealGet(
        allergy = allergy,
        calorie = calorie,
        menu = menu,
        time = time
    )
internal fun List<MealGetResponse>.toModels() =
    this.map {
        it.toModel()
    }