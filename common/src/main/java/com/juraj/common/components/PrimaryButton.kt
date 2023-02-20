package com.juraj.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.juraj.codingtask.ui.theme.Black
import com.juraj.codingtask.ui.theme.Grey2
import com.juraj.codingtask.ui.theme.Yellow

@Composable
fun PrimaryButton(text: String, isEnabled: Boolean = true, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        Modifier.fillMaxWidth(0.9f),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Yellow,
            contentColor = Black,
            disabledBackgroundColor = Grey2
        ),
        enabled = isEnabled,
    ) {
        Text(text, color = Black)
    }
}
