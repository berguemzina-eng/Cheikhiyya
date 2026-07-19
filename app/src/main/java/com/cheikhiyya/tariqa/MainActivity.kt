package com.cheikhiyya.tariqa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.platform.LocalLayoutDirection
import com.cheikhiyya.tariqa.nav.RootNavigation
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
                    RootNavigation()
                }
            }
        }
    }
}
