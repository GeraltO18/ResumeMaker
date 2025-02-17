package com.resumemaker.editor.presentation.ui.components.buttons

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.resumemaker.editor.presentation.ui.components.themes.ButtonShapes
import com.resumemaker.editor.presentation.ui.components.themes.ResumeMakerColors as Colors

sealed class ButtonType {
    object Primary : ButtonType()
    object Secondary : ButtonType()
}

@Composable
fun AppButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    buttonType: ButtonType = ButtonType.Primary
) {
    val colors = when (buttonType) {
        ButtonType.Primary -> ButtonDefaults.buttonColors(containerColor = Colors.Primary)
        ButtonType.Secondary -> ButtonDefaults.buttonColors(containerColor = Colors.Secondary)
    }

    Button(
        onClick = onClick,
        colors = colors,
        shape = ButtonShapes.large,
        modifier = modifier.padding(8.dp)
    ) {
        Text(text = text, color = Colors.TextPrimary)
    }
}