package com.hmibrahimsarkar.kabboloker_brakkhakbi.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

// Golden & Amber Accent Colors
val AmberAccent = Color(0xFFD4A017)
val GoldLight = Color(0xFFF4C842)
val GoldPrimary = Color(0xFFD4A017)
val GoldDark = Color(0xFFB8860B)
val GoldGlow = Color(0xFFFFE57F)

// Gold Gradients
val GoldGradient = Brush.linearGradient(
    colors = listOf(GoldLight, GoldPrimary, GoldDark)
)

val GoldGlowGradient = Brush.radialGradient(
    colors = listOf(GoldLight.copy(alpha = 0.6f), Color.Transparent)
)

// Background & Surface Tokens
val LightBackground = Color(0xFFFAFAF8)
val LightSurface = Color(0xFFFFFFFF)
val LightSurfaceVariant = Color(0xFFF2F2EC)
val LightBorder = Color(0xFFE8E6DC)

val DarkBackground = Color(0xFF0A0E14)
val DarkSurface = Color(0xFF161B22)
val DarkSurfaceVariant = Color(0xFF1C222B)
val DarkBorder = Color(0x1AFFFFFF)

// Text Tokens for high contrast readability
val LightTextPrimary = Color(0xFF111318)
val LightTextSecondary = Color(0xFF4A4A5A)
val DarkTextPrimary = Color(0xFFF2F4FB)
val DarkTextSecondary = Color(0xFFA0A0B5)

// Accent Colors
val SoftLavender = Color(0xFFC9B3E8)
val LavenderDark = Color(0xFF8A6CB3)
val MutedGrey = Color(0xFF8A8A9E)
val RoseAccent = Color(0xFFE57373)
val EmeraldGreen = Color(0xFF81C784)
