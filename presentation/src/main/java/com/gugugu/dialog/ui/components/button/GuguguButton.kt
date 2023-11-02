package com.gugugu.dialog.ui.components.button

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.gugugu.dialog.ui.theme.Body1
import com.gugugu.dialog.ui.theme.GuguguTheme

@Composable
private fun GuguguButton(
    text: String,
    textColor: Color,
    modifier: Modifier,
    buttonColors: ButtonColors,
    enabled: Boolean,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        colors = buttonColors,
        shape = RoundedCornerShape(5.dp),
        onClick = onClick,
        enabled = enabled
    ) {
        Body1(
            text = text,
            textColor = textColor
        )
    }
}

@Composable
fun GuguguDefaultButton(
    text: String,
    textColor: Color = GuguguTheme.color.White,
    modifier: Modifier = Modifier.fillMaxWidth().height(50.dp),
    colors: ButtonColors = ButtonDefaults.buttonColors(containerColor = GuguguTheme.color.Black, disabledContainerColor = GuguguTheme.color.Gray),
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    GuguguButton(
        text = text,
        textColor = textColor,
        modifier = modifier,
        buttonColors = colors,
        enabled = enabled,
        onClick = onClick
    )
}