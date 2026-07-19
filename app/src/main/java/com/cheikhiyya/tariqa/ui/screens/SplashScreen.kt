package com.cheikhiyya.tariqa.ui.screens

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cheikhiyya.tariqa.R
import com.cheikhiyya.tariqa.ui.theme.Gold
import com.cheikhiyya.tariqa.ui.theme.GoldLight
import com.cheikhiyya.tariqa.ui.theme.IslamicPatternBackground
import com.cheikhiyya.tariqa.ui.theme.Primary
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.runtime.rememberCoroutineScope

private const val SPLASH_DURATION_MS = 3000L

@Composable
fun SplashScreen(onFinished: () -> Unit) {
    val scale = remember { Animatable(0.85f) }
    val alpha = remember { Animatable(0f) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch {
            scale.animateTo(1f, animationSpec = tween(durationMillis = 700, easing = FastOutSlowInEasing))
        }
        scope.launch {
            alpha.animateTo(1f, animationSpec = tween(durationMillis = 700, easing = FastOutSlowInEasing))
        }
        delay(SPLASH_DURATION_MS)
        onFinished()
    }

    Box(modifier = Modifier.fillMaxSize().background(Primary)) {
        IslamicPatternBackground(modifier = Modifier.fillMaxSize())

        Column(
            modifier = Modifier.fillMaxSize().padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_placeholder),
                contentDescription = "شعار الطريقة الشيخية",
                modifier = Modifier
                    .size(160.dp)
                    .scale(scale.value)
                    .alpha(alpha.value),
            )
            Spacer(Modifier.height(24.dp))
            Text(
                "الطريقة الشيخية الشاذلية",
                color = Gold,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.alpha(alpha.value),
            )
            Spacer(Modifier.height(6.dp))
            Text(
                "Tariqa Cheikhiyya",
                color = GoldLight,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.alpha(alpha.value),
            )
        }
    }
}
