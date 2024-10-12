package com.mynewchallenge.utils

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mynewchallenge.ui.theme.ColorBlack

@Composable
fun LoadComponent() {
    CircularProgressIndicator(
        color = ColorBlack,
        modifier = Modifier
            .padding(top = 20.dp)
    )
}