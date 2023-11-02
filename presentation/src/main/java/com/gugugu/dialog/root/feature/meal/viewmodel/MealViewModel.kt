package com.gugugu.dialog.root.feature.meal.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.gugugu.dialog.root.feature.meal.mvi.MealSideEffect
import com.gugugu.dialog.root.feature.meal.mvi.MealState
import com.gugugu.dialog.util.getDate
import com.gugugu.domain.usecase.meal.CreateSchoolUseCase
import com.gugugu.domain.usecase.meal.GetMealUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class MealViewModel @Inject constructor(
    private val getMealUseCase: GetMealUseCase,
    private val createSchoolUseCase: CreateSchoolUseCase
): ContainerHost<MealState, MealSideEffect>, ViewModel() {

    override val container: Container<MealState, MealSideEffect> = container(MealState())

    fun load(date: String) = intent {
        getMealUseCase(
            GetMealUseCase.Param(
                date = date
            )
        ).onSuccess {
            Log.d("TAG", "load: $it")
            reduce {
                state.copy(
                    loading = false,
                    mealData = it
                )
            }
        }.onFailure {
            Log.d("TAG", "load: $it")
            postSideEffect(MealSideEffect.ToastError(it))
        }
    }

    fun schoolSave() = intent {
        createSchoolUseCase(
            CreateSchoolUseCase.Param(
                local = "D10",
                schoolCode = "7240454"
            )
        ).onSuccess {

        }.onFailure {
            postSideEffect(MealSideEffect.ToastError(it))
        }
    }
}