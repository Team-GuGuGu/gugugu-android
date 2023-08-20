package com.gugugu.dialog.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.gugugu.dialog.ui.theme.GuguguDarkColor.Pink40
import com.gugugu.dialog.ui.theme.GuguguDarkColor.Pink80
import com.gugugu.dialog.ui.theme.GuguguDarkColor.Purple40
import com.gugugu.dialog.ui.theme.GuguguDarkColor.Purple80
import com.gugugu.dialog.ui.theme.GuguguLightColor.Pink40
import com.gugugu.dialog.ui.theme.GuguguLightColor.Pink80
import com.gugugu.dialog.ui.theme.GuguguLightColor.Purple40
import com.gugugu.dialog.ui.theme.GuguguLightColor.Purple80
import com.gugugu.dialog.ui.theme.GuguguLightColor.PurpleGrey40
import com.gugugu.dialog.ui.theme.GuguguLightColor.PurpleGrey80

//private val DarkColorScheme = darkColorScheme(
//    primary = Purple80,
//    secondary = PurpleGrey80,
//    tertiary = Pink80
//)
//
//private val LightColorScheme = lightColorScheme(
//    primary = Purple40,
//    secondary = PurpleGrey40,
//    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */


@Composable
fun GuguguTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    typography: GuguguTypography = GuguguTheme.typography,
    shape: GuguguShape = GuguguTheme.shape,
    content: @Composable () -> Unit
) {
    val colorPalette = if (darkTheme) {
        GuguguDarkColor
    } else {
        GuguguLightColor
    }
    CompositionLocalProvider(
        LocalColor provides colorPalette,
        LocalTypography provides typography,
        LocalShape provides shape
    ) {
        content()
    }


}


object GuguguTheme {
    val color: GuguguColor
        @Composable
        @ReadOnlyComposable
        get() = LocalColor.current
    val typography: GuguguTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
    val shape: GuguguShape
        @Composable
        @ReadOnlyComposable
        get() = LocalShape.current
}

//@Composable
//fun GuguguThemess(
//    darkTheme: Boolean = isSystemInDarkTheme(),
//    // Dynamic color is available on Android 12+
//    dynamicColor: Boolean = true,
//    content: @Composable () -> Unit
//) {
//    val colorScheme = when {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }
//
//        darkTheme -> DarkColorScheme
//        else -> LightColorScheme
//    }
//    val view = LocalView.current
//    if (!view.isInEditMode) {
//        SideEffect {
//            val window = (view.context as Activity).window
//            window.statusBarColor = colorScheme.primary.toArgb()
//            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
//        }
//    }
//
//    MaterialTheme(
//        colorScheme = colorScheme,
//        typography = Typography,
//        content = content
//    )
//}