package com.cheikhiyya.tariqa.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cheikhiyya.tariqa.data.HIZB_AL_FALAH
import com.cheikhiyya.tariqa.data.HIZB_AL_FALAH_BASMALA
import com.cheikhiyya.tariqa.data.HIZB_AL_FALAH_ISTIADHA
import com.cheikhiyya.tariqa.data.HIZB_AL_FALAH_NOTE
import com.cheikhiyya.tariqa.data.HIZB_AL_FALAH_SOURCE
import com.cheikhiyya.tariqa.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HizbAlFalahScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("حزب الفلاح", color = Gold, fontWeight = FontWeight.Bold) },
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
                modifier = Modifier
                    .fillMaxWidth()
                    .background(GoldLight)
                    .padding(vertical = 6.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(Icons.Filled.WifiOff, contentDescription = null, tint = Primary, modifier = Modifier.size(14.dp))
                Spacer(Modifier.width(6.dp))
                Text("متوفر بدون إنترنت — اضغط على أي فقرة للعد", color = Primary, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
            }

            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                modifier = Modifier.weight(1f),
            ) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Primary, RoundedCornerShape(12.dp))
                            .padding(14.dp),
                    ) {
                        Text(HIZB_AL_FALAH_ISTIADHA, color = GoldLight, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth(), lineHeight = 26.sp)
                        Spacer(Modifier.height(4.dp))
                        Text(HIZB_AL_FALAH_BASMALA, color = GoldLight, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth(), lineHeight = 26.sp)
                    }
                    IslamicDivider(modifier = Modifier.padding(vertical = 10.dp))
                    Spacer(Modifier.height(4.dp))
                }

                itemsIndexed(HIZB_AL_FALAH) { index, line ->
                    DhikrLineCard(index = index, text = line.text, target = line.repeat)
                    Spacer(Modifier.height(10.dp))
                }

                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(BorderColor, RoundedCornerShape(10.dp))
                            .padding(12.dp),
                    ) {
                        Text(HIZB_AL_FALAH_NOTE, color = TextDark, textAlign = TextAlign.Right, fontSize = 13.sp, lineHeight = 22.sp)
                    }
                    Spacer(Modifier.height(14.dp))
                    Text(
                        HIZB_AL_FALAH_SOURCE,
                        color = TextMuted,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    Spacer(Modifier.height(24.dp))
                }
            }
        }
    }
}

@Composable
private fun DhikrLineCard(index: Int, text: String, target: Int) {
    var count by remember { mutableIntStateOf(0) }
    val done = count >= target

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(if (done) Color(0xFFF1E9D2) else Color.White, RoundedCornerShape(10.dp))
            .border(1.dp, if (done) Gold else BorderColor, RoundedCornerShape(10.dp))
            .clickable { count = if (count >= target) 0 else count + 1 }
            .padding(12.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text("${index + 1}", color = TextMuted, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            if (target > 1) {
                Box(
                    modifier = Modifier
                        .background(Primary, RoundedCornerShape(20.dp))
                        .padding(horizontal = 10.dp, vertical = 2.dp),
                ) {
                    Text("$count / $target", color = Gold, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
        Spacer(Modifier.height(6.dp))
        Text(text, color = TextDark, fontSize = 17.sp, textAlign = TextAlign.Right, lineHeight = 30.sp, modifier = Modifier.fillMaxWidth())
    }
}

