package com.gugugu.domain.usecase.meal

import com.gugugu.domain.repository.MealRepository
import javax.inject.Inject

class GetSchoolUseCase @Inject constructor(
    private val mealRepository: MealRepository
) {
    suspend operator fun invoke(param: Param) = kotlin.runCatching {
        mealRepository.getSchool(
            local = param.local,
            school = param.school
        )
    }

    data class Param(
        val local: String,
        val school: String
    )
}