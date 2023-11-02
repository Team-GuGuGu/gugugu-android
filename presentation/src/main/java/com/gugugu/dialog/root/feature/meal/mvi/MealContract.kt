package com.gugugu.dialog.root.feature.meal.mvi

import com.gugugu.domain.model.MealGet


data class MealState(
    val mealData: List<MealGet> = listOf(
        MealGet("", "", "", ""),
        MealGet("", "", "", ""),
        MealGet("", "", "", "")
    ),
    val loading: Boolean = false
)


sealed class MealSideEffect {
    data class ToastError(val throwable: Throwable): MealSideEffect()
}