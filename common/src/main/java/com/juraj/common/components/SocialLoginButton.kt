package com.juraj.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import com.juraj.codingtask.ui.theme.Black
import com.juraj.codingtask.ui.theme.Grey2
import com.juraj.codingtask.ui.theme.Yellow

@Composable
fun SocialLoginButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick, Modifier.fillMaxWidth().padding(16.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = White,
            contentColor = Black,
        )
    ) {
        Text(text, color = Black)
    }
}
