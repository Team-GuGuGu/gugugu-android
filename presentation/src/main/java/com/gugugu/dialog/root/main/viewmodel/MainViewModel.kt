package com.gugugu.dialog.root.main.viewmodel

import androidx.lifecycle.ViewModel
import com.gugugu.dialog.root.main.mvi.MainSideEffect
import com.gugugu.dialog.root.main.mvi.MainState
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
class MainViewModel @Inject constructor(
    private val getMealUseCase: GetMealUseCase,
    private val createSchoolUseCase: CreateSchoolUseCase
): ContainerHost<MainState, MainSideEffect>, ViewModel() {

    override val container: Container<MainState, MainSideEffect> = container(MainState())

    fun load() = intent {
        reduce {
            state.copy(
                loading = false
            )
        }
        getMealUseCase(
            GetMealUseCase.Param(
                date = getDate()
            )
        ).onSuccess {
            reduce {
                state.copy(
                    loading = true,
                    meal = it
                )
            }
        }.onFailure {
            postSideEffect(MainSideEffect.ToastError(it))
        }
    }

    fun schoolSave() = intent {
        createSchoolUseCase(
            CreateSchoolUseCase.Param(
                local = "D10",
                schoolCode = "7240454"
            )
        ).onSuccess {
            load()
        }.onFailure {
            postSideEffect(MainSideEffect.ToastError(it))
        }
    }

}