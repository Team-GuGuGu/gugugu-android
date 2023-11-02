package com.gugugu.dialog.root.feature.meal

import android.util.Log
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.gugugu.dialog.R
import com.gugugu.dialog.ui.theme.Body1_5
import com.gugugu.dialog.ui.theme.BoldBody1_5
import com.gugugu.dialog.ui.theme.BoldBody2
import com.gugugu.dialog.ui.theme.GuguguTheme
import com.gugugu.dialog.ui.theme.HeadLine1
import com.gugugu.dialog.ui.theme.SemiBoldBody2
import com.gugugu.dialog.util.guguguClickable
import com.gugugu.dialog.util.toDp

private fun getMealName(value: Int): String =
    when(value) {
        0 -> "아침"
        1 -> "점심"
        else -> "저녁"
    }

@Composable
@Preview(showBackground = true)
fun MealScreen() {
//    MovingViewCoveringButtons()
    var nowMeal by remember { mutableStateOf(0) }
    val mealName = getMealName(nowMeal)
    GuguguTheme {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 22.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier.size(40.dp),
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
                        text = "2023년 8월 28일"
                    )
                }
                Spacer(modifier = Modifier.width(4.dp))
                Image(
                    modifier = Modifier.size(40.dp),
                    painter = painterResource(id = R.drawable.ic_arrow_right),
                    contentDescription = "날짜 다음날로 넘어가기"
                )
            }
            Spacer(modifier = Modifier.height(22.dp))
            Row(
                modifier = Modifier.padding(horizontal = 46.dp)
            ) {
                MovingButton {
                    nowMeal = it
                }
            }
            Spacer(modifier = Modifier.height(52.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .border(1.dp, GuguguTheme.color.Black, GuguguTheme.shape.middle)
                    .clip(GuguguTheme.shape.middle)
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
                    SemiBoldBody2(text = "치즈닭갈비주먹밥 \n깍두기 \n*고래밥시리얼+우유 \n배도라지주스 \n감자토마토그라탕")
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
                       HeadLine1(text = "482.4 Kcal")
                   }
               }
            }

            Spacer(modifier = Modifier.height(24.dp))

            //알러지
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .border(1.dp, GuguguTheme.color.Black, GuguguTheme.shape.middle)
                    .clip(GuguguTheme.shape.middle)
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
                        BoldBody2(text = "②우유 ⑤대두 ⑥밀 ⑨새우 ⑬아황산류 ⑮닭고기")
                    }
                }
            }
        }
    }
}


@Composable
fun MovingButton(
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
    Column(
        modifier = Modifier
    ) {
        Surface(modifier = Modifier) {
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
                                viewPosition = btn1
                                nowImage = R.drawable.ic_sun
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
                                viewPosition = btn2
                                nowImage = R.drawable.ic_launch
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
                                viewPosition = btn3
                                nowImage = R.drawable.ic_moon
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

