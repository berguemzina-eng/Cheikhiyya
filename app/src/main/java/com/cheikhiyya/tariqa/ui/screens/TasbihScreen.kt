package com.cheikhiyya.tariqa.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cheikhiyya.tariqa.ui.theme.*

// عبارات الذكر التقليدية للسبحة، بالترتيب الشائع عند أهل الطريق
private val DHIKR_PHRASES = listOf(
    "سُبْحَانَ اللهِ",
    "الْحَمْدُ لِلَّهِ",
    "اللهُ أَكْبَرُ",
    "لَا إِلَهَ إِلَّا اللهُ",
    "أَسْتَغْفِرُ اللهَ",
    "اللَّهُمَّ صَلِّ عَلَى سَيِّدِنَا مُحَمَّدٍ",
)

private val TARGETS = listOf(33, 99, 100, 1000)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasbihScreen(onBack: () -> Unit) {
    var count by remember { mutableIntStateOf(0) }
    var target by remember { mutableIntStateOf(33) }
    var phraseIndex by remember { mutableIntStateOf(0) }
    var pressed by remember { mutableStateOf(false) }
    val haptic = LocalHapticFeedback.current

    val scale by animateFloatAsState(if (pressed) 0.94f else 1f, label = "tap-scale")

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(modifier = Modifier.size(30.dp).background(Cream, CircleShape), contentAlignment = Alignment.Center) {
                            androidx.compose.foundation.Image(
                                painter = androidx.compose.ui.res.painterResource(id = com.cheikhiyya.tariqa.R.drawable.logo_circular),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp),
                            )
                        }
                        Spacer(Modifier.width(8.dp))
                        Text("المسبحة", color = Gold, fontWeight = FontWeight.Bold)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "رجوع", tint = Color.White)
                    }
                },
                actions = {
                    IconButton(onClick = { count = 0 }) {
                        Icon(Icons.Filled.Refresh, contentDescription = "إعادة", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Primary),
            )
        },
        containerColor = Cream,
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding).fillMaxSize().padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().background(GoldLight, RoundedCornerShape(8.dp)).padding(vertical = 6.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(Icons.Filled.WifiOff, contentDescription = null, tint = Primary, modifier = Modifier.size(14.dp))
                Spacer(Modifier.width(6.dp))
                Text("مسبحة إسلامية صوفية — متوفرة بدون إنترنت", color = Primary, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
            }

            Spacer(Modifier.height(18.dp))

            // اختيار الذكر
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Primary, RoundedCornerShape(14.dp))
                    .clickable { phraseIndex = (phraseIndex + 1) % DHIKR_PHRASES.size }
                    .padding(vertical = 16.dp, horizontal = 12.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    DHIKR_PHRASES[phraseIndex],
                    color = GoldLight,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                )
            }
            Text(
                "اضغط على الذكر أعلاه لتبديله",
                color = TextMuted,
                fontSize = 11.sp,
                modifier = Modifier.padding(top = 6.dp),
            )

            Spacer(Modifier.height(28.dp))

            // العداد الدائري الكبير — يُضغط للعد
            Box(
                modifier = Modifier
                    .size(220.dp)
                    .scale(scale)
                    .background(Brush.radialGradient(listOf(PrimaryLight, Primary)), CircleShape)
                    .border(3.dp, Gold, CircleShape)
                    .clickable {
                        pressed = true
                        count += 1
                        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                    },
                contentAlignment = Alignment.Center,
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("$count", color = Gold, fontSize = 56.sp, fontWeight = FontWeight.Bold)
                    Text("اضغط للعد", color = GoldLight, fontSize = 13.sp)
                }
            }

            LaunchedEffect(count) {
                if (pressed) {
                    kotlinx.coroutines.delay(100)
                    pressed = false
                }
            }

            Spacer(Modifier.height(10.dp))
            if (count >= target) {
                Text(
                    "بلغت العدد المطلوب ✦ $target",
                    color = Primary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                )
            } else {
                Text(
                    "$count / $target",
                    color = TextMuted,
                    fontSize = 13.sp,
                )
            }

            Spacer(Modifier.height(24.dp))

            // اختيار الهدف
            Text("اختر العدد المستهدف", color = TextDark, fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
            Spacer(Modifier.height(10.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                TARGETS.forEach { t ->
                    val selected = target == t
                    Box(
                        modifier = Modifier
                            .background(if (selected) Primary else Color.White, RoundedCornerShape(10.dp))
                            .border(1.dp, if (selected) Gold else BorderColor, RoundedCornerShape(10.dp))
                            .clickable { target = t }
                            .padding(horizontal = 16.dp, vertical = 10.dp),
                    ) {
                        Text("$t", color = if (selected) Gold else TextDark, fontWeight = FontWeight.SemiBold)
                    }
                }
            }

            Spacer(Modifier.height(20.dp))

            TextButton(onClick = { count = 0 }) {
                Icon(Icons.Filled.Refresh, contentDescription = null, tint = Primary, modifier = Modifier.size(16.dp))
                Spacer(Modifier.width(4.dp))
                Text("إعادة تصفير العداد", color = Primary, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}
