package com.aminovic.loula.presentation.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun LOULATheme(
    content: @Composable () -> Unit
) {

    CompositionLocalProvider(LocalSpacing provides Dimensions()) {
        MaterialTheme(
            colors = LolaMusicColors,
            typography = LolaMusicTypography,
            shapes = LolaMusicShapes,
            content = content
        )
    }
}