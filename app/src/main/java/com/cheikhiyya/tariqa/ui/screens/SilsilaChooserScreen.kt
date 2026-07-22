package com.cheikhiyya.tariqa.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ChartTimeline
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cheikhiyya.tariqa.R
import com.cheikhiyya.tariqa.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SilsilaChooserScreen(onBack: () -> Unit, onChoose: (String) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(modifier = Modifier.size(30.dp).background(Cream, CircleShape), contentAlignment = Alignment.Center) {
                            Image(painter = painterResource(id = R.drawable.logo_circular), contentDescription = null, modifier = Modifier.size(24.dp))
                        }
                        Spacer(Modifier.width(8.dp))
                        Text("سلسلة الطريقة", color = Gold, fontWeight = FontWeight.Bold)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "رجوع", tint = Color.White)
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
            Spacer(Modifier.height(12.dp))
            IslamicMedallionBackdrop(modifier = Modifier.size(90.dp))
            Spacer(Modifier.height(4.dp))
            Text(
                "اختر عرض السلسلة",
                color = Primary,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp,
                textAlign = TextAlign.Center,
            )
            Text(
                "سند الطريقة الشيخية الشاذلية من النبي ﷺ إلى اليوم",
                color = TextMuted,
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 4.dp, bottom = 24.dp),
            )

            ChooserCard(
                title = "سلسلة الطريقة 1",
                subtitle = "العرض المختصر بالأسماء والتواريخ",
                onClick = { onChoose("silsila") },
            )
            Spacer(Modifier.height(14.dp))
            ChooserCard(
                title = "سلسلة الطريقة 2",
                subtitle = "العرض المفصّل بالأسماء الكاملة والسلاسل البديلة",
                onClick = { onChoose("silsila2") },
            )
        }
    }
}

@Composable
private fun ChooserCard(title: String, subtitle: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(2.dp, RoundedCornerShape(16.dp))
            .background(Color.White, RoundedCornerShape(16.dp))
            .border(1.dp, BorderColor, RoundedCornerShape(16.dp))
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier.size(46.dp).background(Primary, CircleShape),
            contentAlignment = Alignment.Center,
        ) {
            Icon(Icons.Filled.ChartTimeline, contentDescription = null, tint = Gold, modifier = Modifier.size(22.dp))
        }
        Spacer(Modifier.width(14.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(title, color = TextDark, fontWeight = FontWeight.Bold, fontSize = 15.sp, textAlign = TextAlign.Right)
            Spacer(Modifier.height(2.dp))
            Text(subtitle, color = TextMuted, fontSize = 12.sp, textAlign = TextAlign.Right, lineHeight = 17.sp)
        }
        Icon(Icons.AutoMirrored.Filled.ArrowBackIosNew, contentDescription = null, tint = Primary, modifier = Modifier.size(16.dp))
    }
}
