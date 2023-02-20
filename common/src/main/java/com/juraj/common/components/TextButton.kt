package com.juraj.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.juraj.codingtask.ui.theme.Black
import com.juraj.codingtask.ui.theme.Yellow

@Composable
fun SecondaryTextButton(text: String, onClick: () -> Unit) {
    TextButton(onClick, Modifier.fillMaxWidth(0.9f)) {
        Text(text = text, color = Black)
    }
}