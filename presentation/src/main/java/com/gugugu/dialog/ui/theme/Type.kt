package com.gugugu.dialog.ui.theme

import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
object GuguguTypography {

    @Stable
    val body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.5.sp
    )
    @Stable
    val title1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )

}

@Composable
fun Body1(
    text: String,
    textColor: Color = GuguguTheme.color.Black,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        color = textColor,
        modifier = modifier,
        style = GuguguTypography.body1
    )
}

@Composable
fun Title1(
    text: String,
    textColor: Color = GuguguTheme.color.Black,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        color = textColor,
        modifier = modifier,
        style = GuguguTypography.title1
    )
}

val LocalTypography = staticCompositionLocalOf { GuguguTypography }
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */