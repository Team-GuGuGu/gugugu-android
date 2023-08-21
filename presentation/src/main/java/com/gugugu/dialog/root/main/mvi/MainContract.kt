package com.gugugu.dialog.root.main.mvi

import com.gugugu.domain.model.MealGet
import java.lang.Exception

data class MainState(
    val loading: Boolean = false,
    val meal: List<MealGet> = listOf()
)


sealed class MainSideEffect {
    data class ToastError(val throwable: Throwable): MainSideEffect()
}