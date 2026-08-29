package com.cheikhiyya.tariqa.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.WifiOff
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
import com.cheikhiyya.tariqa.data.SILSILA_2_CHAIN
import com.cheikhiyya.tariqa.data.SILSILA_2_NOTE
import com.cheikhiyya.tariqa.data.SILSILA_SOURCE
import com.cheikhiyya.tariqa.data.SilsilaLink2
import com.cheikhiyya.tariqa.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Silsila2Screen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(modifier = Modifier.size(30.dp).background(Cream, CircleShape), contentAlignment = Alignment.Center) {
                            Image(painter = painterResource(id = R.drawable.logo_circular), contentDescription = null, modifier = Modifier.size(24.dp))
                        }
                        Spacer(Modifier.width(8.dp))
                        Text("سلسلة الطريقة (مفصلة)", color = Gold, fontWeight = FontWeight.Bold, fontSize = 16.sp)
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
        Column(modifier = Modifier.padding(padding).fillMaxSize()) {
            Row(
                modifier = Modifier.fillMaxWidth().background(GoldLight).padding(vertical = 7.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(Icons.Filled.WifiOff, contentDescription = null, tint = Primary, modifier = Modifier.size(14.dp))
                Spacer(Modifier.width(6.dp))
                Text("بالأسماء الكاملة والسلاسل البديلة", color = Primary, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
            }

            LazyColumn(contentPadding = PaddingValues(18.dp)) {
                item {
                    Column(
                        modifier = Modifier.fillMaxWidth().background(Primary, RoundedCornerShape(16.dp)).padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            "النسخة الموسّعة، بالأسماء الكاملة وذكر الشيوخ البدلاء عند تعدد الأخذ",
                            color = GoldLight,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            textAlign = TextAlign.Center,
                            lineHeight = 20.sp,
                        )
                    }
                    Spacer(Modifier.height(20.dp))
                }

                itemsIndexed(SILSILA_2_CHAIN) { index, link ->
                    Silsila2Row(index = index, link = link, isLast = index == SILSILA_2_CHAIN.lastIndex)
                }

                item {
                    Spacer(Modifier.height(6.dp))
                    IslamicDivider()
                    Spacer(Modifier.height(14.dp))
                    Column(
                        modifier = Modifier.fillMaxWidth().shadow(2.dp, RoundedCornerShape(14.dp)).background(Primary, RoundedCornerShape(14.dp)).padding(16.dp),
                    ) {
                        SILSILA_2_NOTE.forEach {
                            Text(it, color = GoldLight, textAlign = TextAlign.Right, fontSize = 13.sp, lineHeight = 23.sp, modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp))
                        }
                    }
                    Spacer(Modifier.height(14.dp))
                    Text(SILSILA_SOURCE, color = TextMuted, fontSize = 12.sp, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
                    Spacer(Modifier.height(24.dp))
                }
            }
        }
    }
}

@Composable
private fun Silsila2Row(index: Int, link: SilsilaLink2, isLast: Boolean) {
    val isMilestone = link.altTeacher != null

    Row(modifier = Modifier.fillMaxWidth()) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.width(42.dp)) {
            Box(
                modifier = Modifier
                    .size(if (isMilestone) 36.dp else 30.dp)
                    .shadow(2.dp, CircleShape)
                    .background(if (isMilestone) Gold else Primary, CircleShape)
                    .border(1.5.dp, if (isMilestone) Primary else Gold, CircleShape),
                contentAlignment = Alignment.Center,
            ) {
                if (isMilestone) {
                    Icon(Icons.Filled.Star, contentDescription = null, tint = Primary, modifier = Modifier.size(16.dp))
                } else {
                    Text("${index + 1}", color = Gold, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }
            if (!isLast) {
                Box(
                    modifier = Modifier
                        .width(3.dp)
                        .weight(1f)
                        .defaultMinSize(minHeight = 30.dp)
                        .background(Gold.copy(alpha = 0.55f)),
                )
            }
        }
        Spacer(Modifier.width(12.dp))
        Column(modifier = Modifier.padding(bottom = 20.dp).weight(1f)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(if (isMilestone) 3.dp else 1.dp, RoundedCornerShape(12.dp))
                    .background(if (isMilestone) Primary else Color.White, RoundedCornerShape(12.dp))
                    .border(1.dp, if (isMilestone) Gold else BorderColor, RoundedCornerShape(12.dp))
                    .padding(horizontal = 14.dp, vertical = 12.dp),
            ) {
                Column {
                    Text(
                        link.name,
                        color = if (isMilestone) GoldLight else TextDark,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        textAlign = TextAlign.Right,
                        lineHeight = 23.sp,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    link.altTeacher?.let {
                        Spacer(Modifier.height(8.dp))
                        Box(
                            modifier = Modifier
                                .background(Gold.copy(alpha = 0.16f), RoundedCornerShape(8.dp))
                                .padding(horizontal = 10.dp, vertical = 6.dp),
                        ) {
                            Text(it, color = GoldLight, fontSize = 12.sp, textAlign = TextAlign.Right, lineHeight = 18.sp)
                        }
                    }
                }
            }
        }
    }
}
