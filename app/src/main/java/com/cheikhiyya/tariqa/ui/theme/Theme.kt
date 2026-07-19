package com.cheikhiyya.tariqa.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val CheikhiyyaColorScheme = lightColorScheme(
    primary = Primary,
    onPrimary = Cream,
    secondary = Gold,
    onSecondary = Primary,
    background = Cream,
    onBackground = TextDark,
    surface = Cream,
    onSurface = TextDark,
)

private val CheikhiyyaTypography = Typography(
    bodyLarge = TextStyle(fontSize = 16.sp, lineHeight = 26.sp),
    titleLarge = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
    titleMedium = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.SemiBold),
)

@Composable
fun TariqaCheikhiyyaTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = CheikhiyyaColorScheme,
        typography = CheikhiyyaTypography,
        content = content,
    )
}
