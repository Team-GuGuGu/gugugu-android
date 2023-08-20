package com.gugugu.dialog.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color


interface GuguguColor {
    val White: Color
    val Gray: Color
    val DarkGray: Color
    val Red: Color

    val Purple80: Color
    val PurpleGrey80: Color
    val Pink80: Color

    val Purple40: Color
    val PurpleGrey40: Color
    val Pink40: Color

    val Black: Color
    val Black80: Color
    val Black60: Color
    val Black40: Color
    val Black20: Color
}
object GuguguLightColor: GuguguColor {
    override val White = Color(0xFFFFFFFF)
    override val Gray = Color(0xFFE8E8E8)
    override val DarkGray = Color(0xCC4F4F4F)
    override val Red = Color(0xFFFF0D0D)

    override val Purple80 = Color(0xFFD0BCFF)
    override val PurpleGrey80 = Color(0xFFCCC2DC)
    override val Pink80 = Color(0xFFEFB8C8)

    override val Purple40 = Color(0xFF6650a4)
    override val PurpleGrey40 = Color(0xFF625b71)
    override val Pink40 = Color(0xFF7D5260)

    override val Black = Color(0xFF000000)
    override val Black80 = Color(0xCC000000)
    override val Black60 = Color(0x99000000)
    override val Black40 = Color(0x66000000)
    override val Black20: Color = Color(0x33000000)
}

object GuguguDarkColor: GuguguColor {
    override val White = Color(0xFFFFFFFF)
    override val Gray = Color(0xFFFF0D0D)//Color(0xFFE8E8E8)
    override val DarkGray = Color(0xCC4F4F4F)
    override val Red = Color(0xFFFF0D0D)

    override val Purple80 = Color(0xFFD0BCFF)
    override val PurpleGrey80 = Color(0xFFCCC2DC)
    override val Pink80 = Color(0xFFEFB8C8)

    override val Purple40 = Color(0xFF6650a4)
    override val PurpleGrey40 = Color(0xFF625b71)
    override val Pink40 = Color(0xFF7D5260)

    override val Black = Color(0xFF000000)
    override val Black80 = Color(0xCC000000)
    override val Black60 = Color(0x99000000)
    override val Black40 = Color(0x66000000)
    override val Black20: Color = Color(0x33000000)
}


internal val LocalColor = compositionLocalOf<GuguguColor> {
    error("No Color Provided")
}