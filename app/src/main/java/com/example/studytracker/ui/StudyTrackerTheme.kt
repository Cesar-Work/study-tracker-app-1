package com.example.studytracker.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/**
 * Defines the light and dark color palettes for the application.
 */
private val LightColors = lightColorScheme()

private val DarkColors = darkColorScheme()

/**
 * Provides a simple Material3 theme wrapper for the application. This wrapper switches
 * between light and dark color schemes based on the system setting and applies
 * default typography and shapes. All composables inside this theme inherit these styles.
 */
@Composable
fun StudyTrackerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors
    MaterialTheme(
        colorScheme = colors,
        typography = MaterialTheme.typography,
        content = content
    )
}