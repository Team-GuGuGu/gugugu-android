package com.gugugu.domain.usecase.meal

import com.gugugu.domain.repository.MealRepository
import javax.inject.Inject

class GetMealUseCase @Inject constructor(
    private val mealRepository: MealRepository
) {
    suspend operator fun invoke(param: Param) = kotlin.runCatching {
        mealRepository.getMeal(
            date = param.date
        )
    }

    data class Param(
        val date: String
    )

}