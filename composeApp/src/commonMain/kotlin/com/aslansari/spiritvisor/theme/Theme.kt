package com.aslansari.spiritvisor.theme

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

val lightThemeColors = Colors(
    primary = primaryLight,
    primaryVariant = primaryContainerLight,
    secondary = secondaryLight,
    secondaryVariant = secondaryContainerLight,
    background = backgroundLight,
    surface = surfaceLight,
    error= errorLight,
    onPrimary = onPrimaryLight,
    onSecondary = onSecondaryLight,
    onBackground = onBackgroundLight,
    onSurface = onSurfaceLight,
    onError = onErrorLight,
    isLight = true
)
@Composable
fun SpiritVisorTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = lightThemeColors,
        typography = typography,
        shapes = shapes,
        content = content,
    )
}