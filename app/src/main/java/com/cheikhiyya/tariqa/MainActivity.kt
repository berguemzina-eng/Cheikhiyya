package com.cheikhiyya.tariqa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import com.cheikhiyya.tariqa.nav.RootNavigation
import com.cheikhiyya.tariqa.ui.screens.SplashScreen
import com.cheikhiyya.tariqa.ui.theme.TariqaCheikhiyyaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // Toda la interfaz se fuerza a derecha-a-izquierda (árabe),
            // sin depender de la configuración regional del teléfono.
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                TariqaCheikhiyyaTheme {
                    AppRoot()
                }
            }
        }
    }
}

@Composable
private fun AppRoot() {
    var showSplash by remember { mutableStateOf(true) }
    if (showSplash) {
        SplashScreen(onFinished = { showSplash = false })
    } else {
        RootNavigation()
    }
}
