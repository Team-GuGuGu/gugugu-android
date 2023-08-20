package com.gugugu.domain.usecase.meal

import com.gugugu.domain.repository.MealRepository
import javax.inject.Inject

class CreateSchoolUseCase @Inject constructor(
    private val mealRepository: MealRepository
) {
    suspend operator fun invoke(param: Param) = kotlin.runCatching {
        mealRepository.createSchool(
            local = param.local,
            schoolCode = param.schoolCode
        )
    }

    data class Param(
        val local: String,
        val schoolCode: String
    )
}