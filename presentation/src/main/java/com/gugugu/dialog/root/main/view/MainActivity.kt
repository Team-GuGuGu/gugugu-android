package com.gugugu.dialog.root.main.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gugugu.dialog.root.main.mvi.MainSideEffect
import com.gugugu.dialog.root.main.mvi.MainState
import com.gugugu.dialog.root.main.viewmodel.MainViewModel
import com.gugugu.dialog.ui.theme.Body1
import com.gugugu.dialog.ui.theme.GuguguTheme
import com.gugugu.dialog.ui.theme.GuguguTypography.title1
import com.gugugu.dialog.ui.theme.Title1
import com.gugugu.dialog.util.toTime
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe
import retrofit2.http.Body
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel.observe(this, state = ::render, sideEffect = ::mainSideEffect)
        mainViewModel.schoolSave()
    }

    private fun mainSideEffect(sideEffect: MainSideEffect) {
        when(sideEffect) {
            is MainSideEffect.ToastError -> {
                Log.e("TAG", "mainSideEffect: ${sideEffect.throwable.message}")
            }
        }
    }

    private fun render(state: MainState) {
        state.loading.let {
            if (it) {
                setContent {
                    GuguguTheme {
                        // A surface container using the 'background' color from the theme
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colorScheme.background
                        ) {

                            LazyColumn() {
                                itemsIndexed(
                                    state.meal
                                ) { index, item ->
                                    MealCard(
                                        time = item.time.toTime(),
                                        meal = item.menu,
                                        calorie = item.calorie
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

    }

}

@Preview(showBackground = true)
@Composable
private fun MealCard(
    time: String = "아침",
    meal: String = "영양닭죽 전남친베이글 석박지 사과 *고래밥시리얼+우유",
    calorie: String = "774.8 Kcal"
) {
    GuguguTheme {
        Box(modifier = Modifier
            .border(
                width = (0.5).dp,
                shape = RoundedCornerShape(10.dp),
                color = GuguguTheme.color.Black
            )
            .fillMaxWidth()
            .height(120.dp))
        {
            Column (
                modifier = Modifier.padding(8.dp, 0.dp),
            ) {
                Row(
                    verticalAlignment = Alignment.Bottom
                ) {
                    Title1(time)
                    Spacer(modifier = Modifier.width(20.dp))
                    Body1(text = calorie)

                }
                Column {
                    Spacer(modifier = Modifier.height(5.dp))
                    Body1(text = meal)
                }
            }
        }
    }
}
