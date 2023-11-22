package com.gugugu.dialog.root.feature.meal.view

import android.util.Log
import android.view.MotionEvent
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gugugu.dialog.R
import com.gugugu.dialog.root.feature.meal.mvi.MealSideEffect
import com.gugugu.dialog.root.feature.meal.viewmodel.MealViewModel
import com.gugugu.dialog.ui.theme.Body1_5
import com.gugugu.dialog.ui.theme.BoldBody1_5
import com.gugugu.dialog.ui.theme.BoldBody2
import com.gugugu.dialog.ui.theme.GuguguTheme
import com.gugugu.dialog.ui.theme.HeadLine1
import com.gugugu.dialog.ui.theme.SemiBoldBody2
import com.gugugu.dialog.util.getDate
import com.gugugu.dialog.util.getStringDate
import com.gugugu.dialog.util.guguguClickable
import com.gugugu.dialog.util.nowDay
import com.gugugu.dialog.util.toDp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import java.time.LocalDate

private fun getMealName(value: Int): String =
    when(value) {
        0 -> "아침"
        1 -> "점심"
        else -> "저녁"
    }

@OptIn(ExperimentalComposeUiApi::class)
@Composable
@Preview(showBackground = true)
fun MealScreen(
    viewModel: MealViewModel = hiltViewModel()
) {
//    MovingViewCoveringButtons()
    val state = viewModel.collectAsState().value
    val context = LocalContext.current
    viewModel.collectSideEffect {
        when(it) {
            is MealSideEffect.ToastError -> {
                Toast.makeText(context, it.throwable.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
    var nowMeal by remember { mutableStateOf(0) }
    var date by remember { mutableStateOf(nowDay()) }
    val mealName = getMealName(nowMeal)
    var nowDayPosition by remember { mutableStateOf(0) }
    var test by remember { mutableStateOf(false) }

    var cursor by remember { mutableStateOf(0f) }
    LaunchedEffect(key1 = true) {
        Log.d("TAG", "MealScreen: call")
//        viewModel.schoolSave()
        viewModel.load(date.getDate())
    }
    LaunchedEffect(key1 = date) {
        if (test) {
            viewModel.load(date.getDate())
//            withContext(Dispatchers.Main) {
//                Log.d("TAG", "MealScreen: 날짜 변경")
//                nowDayPosition = 0
//            }
        }
        test = true
    }

//    LaunchedEffect(key1 = cursor) {
//        Log.d("TAG", "MealScreen: $cursor")
//    }
//    AnimatedVisibility(
//        visible = state.loading,
//        enter = fadeIn(),
//        exit = fadeOut()
//    ) {
//
//    }
    AnimatedVisibility(
        modifier = Modifier
            .pointerInteropFilter {
                when(it.action) {
                    MotionEvent.ACTION_DOWN -> {
                        cursor = it.x
                    }
                    MotionEvent.ACTION_UP -> {
                        Log.d("TAG", "MealScreen: ${cursor-it.x}")
                        if (cursor-it.x <= -200) {
                            if (nowMeal != 2) {
                                nowMeal++
                                nowDayPosition++
                            }
                        } else if (cursor-it.x > 200) {
                            if (nowMeal > 0) {
                                nowMeal--
                                nowDayPosition--
                            }
                        }
                    }
                    else -> false
                }
                true
            }
//                awaitEachGesture {
//
//                    val down = awaitFirstDown()
//                    val originalPressPoint = down.position
//                    Log.d("Compose", "화면을 누른 위치: ${originalPressPoint.x}, ${originalPressPoint.y}")
//                    awaitEachGesture {
//
//                    }
//                    val up = awaitFirstDown()
//                    val releasePoint = up.position
//                    Log.d("Compose", "화면에서 땐 위치: ${releasePoint.x}, ${releasePoint.y}")
//
//                }
        ,
        visible = state.loading.not(),
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        GuguguTheme {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
                    .background(Color(0xFFEFECEC))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 22.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        modifier = Modifier
                            .size(40.dp)
                            .guguguClickable {
//                                nowDayPosition = 1
                                date = date.minusDays(1)
                                nowDayPosition = 0
                            },
                        painter = painterResource(id = R.drawable.ic_arrow_left),
                        contentDescription = "날짜 전날로 넘어가기"
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Surface(
                        shape = GuguguTheme.shape.large,
                        border = BorderStroke(1.dp, GuguguTheme.color.Black),
                        color = GuguguTheme.color.White,
                    ) {
                        Body1_5(
                            modifier = Modifier.padding(horizontal = 22.dp, vertical = 10.dp),
                            text = date.getStringDate()?: ""
                        )
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    Image(
                        modifier = Modifier
                            .size(40.dp)
                            .guguguClickable {
//                                nowDayPosition = 2
                                date = date.plusDays(1)
                                nowDayPosition = 0
                            },
                        painter = painterResource(id = R.drawable.ic_arrow_right),
                        contentDescription = "날짜 다음날로 넘어가기"
                    )
                }
                Spacer(modifier = Modifier.height(22.dp))
                Row(
                    modifier = Modifier.padding(horizontal = 46.dp)
                ) {
                    MovingButton(
                        nowPosition = nowDayPosition
                    ) {
                        nowMeal = it
                        nowDayPosition = it
                    }
                }
                Spacer(modifier = Modifier.height(52.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .border(1.dp, GuguguTheme.color.Black, GuguguTheme.shape.middle)
                        .clip(GuguguTheme.shape.middle)
                        .background(GuguguTheme.color.White)
                ) {
                    Column(
                        modifier = Modifier.padding(horizontal = 15.dp)
                    ) {
                        Spacer(modifier = Modifier.height(12.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                modifier = Modifier.size(60.dp),
                                painter = painterResource(id =
                                when(nowMeal) {
                                    0 -> R.drawable.ic_sun
                                    1 -> R.drawable.ic_launch
                                    else -> R.drawable.ic_moon
                                }
                                ),
                                contentDescription = "$mealName 아이콘"
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            HeadLine1(text = mealName)
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        BoldBody1_5(text = "오늘의 ${mealName}은?")
                        Spacer(modifier = Modifier.height(9.dp))
                        SemiBoldBody2(text = state.mealData[nowMeal].menu)
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }

                Spacer(modifier = Modifier.height(25.dp))
                //칼로리

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .border(1.dp, GuguguTheme.color.Black, GuguguTheme.shape.middle)
                        .clip(GuguguTheme.shape.middle)
                        .background(GuguguTheme.color.White)

                ) {
                    Row(
                        modifier = Modifier.padding(top = 29.dp, bottom = 26.dp)
                    ) {
                        Spacer(modifier = Modifier.width(14.dp))
                        Image(
                            modifier = Modifier.size(60.dp),
                            painter = painterResource(id = R.drawable.ic_fire),
                            contentDescription = "칼로리 아이콘"
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Column {
                            HeadLine1(text = "${mealName}의 칼로리는?")
                            Spacer(modifier = Modifier.height(1.dp))
                            HeadLine1(text = state.mealData[nowMeal].calorie)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                //알러지
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    shape = GuguguTheme.shape.middle,
                    color = GuguguTheme.color.White,
                    border = BorderStroke(1.dp, GuguguTheme.color.Black)
                ) {
                    Row(
                        modifier = Modifier.padding(top = 29.dp, bottom = 26.dp, start = 14.dp, end = 14.dp)
                    ) {
                        Image(
                            modifier = Modifier.size(60.dp),
                            painter = painterResource(id = R.drawable.ic_hand),
                            contentDescription = "알러지 아이콘"
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Column {
                            HeadLine1(text = "알러지 정보는?")
                            Spacer(modifier = Modifier.height(1.dp))
                            BoldBody2(text = state.mealData[nowMeal].allergy)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun MovingButton(
    nowPosition: Int = 0,
    action: (Int) -> Unit
) {
    val cornerShape = 100.dp
    val density: Density = LocalDensity.current
    var isVisible by remember { mutableStateOf(false) }
    var buttonSize by remember { mutableStateOf(0.dp) }
    var viewPosition by remember { mutableStateOf(0.dp) }

    val buttonColor = ButtonDefaults.buttonColors(containerColor = Color.Gray)
    var btn1 by remember { mutableStateOf(0.dp) }
    var btn2 by remember { mutableStateOf(0.dp) }
    var btn3 by remember { mutableStateOf(0.dp) }

    val animatedXPosition by animateDpAsState(
        targetValue = viewPosition,
        label = "",
        finishedListener = {

        }
    )
    var nowImage by remember { mutableStateOf(R.drawable.ic_sun) }
    LaunchedEffect(key1 = nowPosition) {
        when (nowPosition) {
            0 -> {
                action(nowPosition)
                viewPosition = btn1
                nowImage = R.drawable.ic_sun
            }
            1 -> {
                action(nowPosition)
                viewPosition = btn2
                nowImage = R.drawable.ic_launch
            }
            else -> {
                action(nowPosition)
                viewPosition = btn3
                nowImage = R.drawable.ic_moon
            }
        }
    }
    Column(
        modifier = Modifier
    ) {
        Surface(
            modifier = Modifier,
            shape = GuguguTheme.shape.middle,
            color = Color(0xFFEFECEC)
        ) {
            Box {
                Row(
                    modifier = Modifier
                        .border(1.dp, SolidColor(Color.Black), RoundedCornerShape(100.dp))
                        .fillMaxWidth()
                        .height(40.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .onGloballyPositioned { coordinates ->
                                btn1 = coordinates.positionInParent().x.toDp(density)
                            }
                            .clip(RoundedCornerShape(cornerShape, 0.dp, 0.dp, cornerShape))
                            .guguguClickable {
                                action(0)
//                                viewPosition = btn1
//                                nowImage = R.drawable.ic_sun
                            }
                            .background(Color(0xFFE4E4E4)),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier.size(30.dp)
                        ) {
                            Image(
                                modifier = Modifier.fillMaxSize(),
                                painter = painterResource(id = R.drawable.ic_sun),
                                contentDescription = "sun_icon",
                                contentScale = ContentScale.Crop,
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .onGloballyPositioned { coordinates ->
                                btn2 = coordinates.positionInParent().x.toDp(density)
                                with(coordinates.size) {
                                    buttonSize = width.toDp(density)
                                    isVisible = true
                                }
                            }
                            .guguguClickable {
                                action(1)
//                                viewPosition = btn2
//                                nowImage = R.drawable.ic_launch
                            }
                            .clip(RoundedCornerShape(0.dp, 0.dp, 0.dp, 0.dp))
                            .background(Color(0xFFE4E4E4)),
                        contentAlignment = Alignment.Center
                    ) {

                        Image(
                            modifier = Modifier.size(30.dp),
                            painter = painterResource(id = R.drawable.ic_launch),
                            contentDescription = "sun_icon",
                            contentScale = ContentScale.Crop
                        )
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .onGloballyPositioned { coordinates ->
                                btn3 = coordinates.positionInParent().x.toDp(density)
                            }
                            .guguguClickable {
                                action(2)
//                                viewPosition = btn3
//                                nowImage = R.drawable.ic_moon
                            }
                            .clip(RoundedCornerShape(0.dp, cornerShape, cornerShape, 0.dp))
                            .background(Color(0xFFE4E4E4)),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            modifier = Modifier.size(30.dp),
                            painter = painterResource(id = R.drawable.ic_moon),
                            contentDescription = "moon_icon",
                            contentScale = ContentScale.Crop
                        )
                    }
                }
                Surface(
                    modifier = Modifier
                        .absoluteOffset(animatedXPosition),
                    color = Color.White,
                    shape = RoundedCornerShape(cornerShape)
                ) {
                    Box(
                        modifier = Modifier
                            .height(40.dp)
                            .width(buttonSize)
                            .border(1.dp, SolidColor(Color.Black), RoundedCornerShape(cornerShape)),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .size(30.dp),
                            painter = painterResource(id = nowImage),
                            contentDescription = ""
                        )
                    }
                }
            }
        }

    }

}

@Composable
fun MovingViewCoveringButtons() {
    val TAG = "LOG"
    var isViewVisible by remember { mutableStateOf(false) }
    var viewXPosition by remember { mutableStateOf(0.dp) }
    var btn1 by remember { mutableStateOf(0.dp) }
    var btn2 by remember { mutableStateOf(0.dp) }
    var btn3 by remember { mutableStateOf(0.dp) }
    val density: Density = LocalDensity.current

    val animatedXPosition by animateDpAsState(
        targetValue = viewXPosition,
        label = "",
        finishedListener = {

        }
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    modifier = Modifier
                        .onGloballyPositioned { coordinates ->
                            btn1 = with(density) { coordinates.positionInParent().x.toDp() }
                        }
                        .size(30.dp),
                    onClick = {
                        isViewVisible = true
                        viewXPosition = btn1
                        Log.d(TAG, "MovingViewCoveringButtons: $viewXPosition")
                    }
                ) {
                    Text("Button 1")
                }
                Button(
                    modifier = Modifier
                        .onGloballyPositioned { coordinates ->
                            btn2 = with(density) { coordinates.positionInParent().x.toDp() }
                        }
                        .size(30.dp),
                    onClick = {
                        isViewVisible = true
                        viewXPosition = btn2
                        Log.d(TAG, "MovingViewCoveringButtons: $viewXPosition")
                    }
                ) {
                    Text("Button 2")
                }
                Button(
                    modifier = Modifier
                        .onGloballyPositioned { coordinates ->
                            btn3 = with(density) { coordinates.positionInParent().x.toDp() }

                        }
                        .size(30.dp),
                    onClick = {
                        isViewVisible = true
                        viewXPosition = btn3
                        Log.d(TAG, "MovingViewCoveringButtons: $viewXPosition")
                    }
                ) {
                    Text("Button 3")
                }
            }
            Button(
                onClick = { },
                modifier = Modifier
                    .absoluteOffset(x = animatedXPosition)
                    .size(30.dp)
                    .border(1.dp, SolidColor(Color.Red), RoundedCornerShape(5.dp))
            ) {}

            Spacer(modifier = Modifier.height(16.dp))

        }
    }
}

