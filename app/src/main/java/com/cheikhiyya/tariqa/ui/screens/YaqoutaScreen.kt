package com.cheikhiyya.tariqa.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.cheikhiyya.tariqa.data.*
import com.cheikhiyya.tariqa.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun YaqoutaScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("الياقوتة", color = Gold, fontWeight = FontWeight.Bold) },
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
                modifier = Modifier.fillMaxWidth().background(GoldLight).padding(vertical = 6.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(Icons.Filled.WifiOff, contentDescription = null, tint = Primary, modifier = Modifier.size(14.dp))
                Spacer(Modifier.width(6.dp))
                Text("متوفر بدون إنترنت — 178 بيتا", color = Primary, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
            }

            LazyColumn(contentPadding = PaddingValues(16.dp)) {
                item {
                    Column(
                        modifier = Modifier.fillMaxWidth().background(Primary, RoundedCornerShape(12.dp)).padding(14.dp),
                    ) {
                        Text(YAQOUTA_BASMALA, color = GoldLight, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
                        Spacer(Modifier.height(8.dp))
                        Text(YAQOUTA_INTRO, color = GoldLight, textAlign = TextAlign.Right, fontSize = 13.sp, lineHeight = 22.sp, modifier = Modifier.fillMaxWidth())
                    }
                    IslamicDivider(modifier = Modifier.padding(vertical = 10.dp))
                    Spacer(Modifier.height(4.dp))
                }

                itemsIndexed(YAQOUTA_OPENING) { index, line ->
                    OpeningCard(index = index, text = line.text)
                    Spacer(Modifier.height(14.dp))
                }

                items(YAQOUTA_VERSES) { verse ->
                    YaqoutaVerseCard(verse)
                    Spacer(Modifier.height(8.dp))
                }

                item {
                    Spacer(Modifier.height(10.dp))
                    Text(
                        YAQOUTA_CLOSING,
                        color = Primary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    Spacer(Modifier.height(10.dp))
                    Text(
                        YAQOUTA_SOURCE,
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
private fun OpeningCard(index: Int, text: String) {
    var tapped by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(if (tapped) Color(0xFFF1E9D2) else Color.White, RoundedCornerShape(10.dp))
            .border(1.dp, if (tapped) Gold else BorderColor, RoundedCornerShape(10.dp))
            .clickable { tapped = !tapped }
            .padding(12.dp),
    ) {
        Text(text, color = TextDark, fontSize = 16.sp, fontWeight = FontWeight.SemiBold, textAlign = TextAlign.Right, lineHeight = 28.sp, modifier = Modifier.fillMaxWidth())
    }
}

@Composable
private fun YaqoutaVerseCard(verse: YaqoutaVerse) {
    val parts = verse.text.split(" == ")
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(10.dp))
            .border(1.dp, BorderColor, RoundedCornerShape(10.dp))
            .padding(horizontal = 12.dp, vertical = 10.dp),
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Box(
                modifier = Modifier.background(Primary, RoundedCornerShape(20.dp)).padding(horizontal = 8.dp, vertical = 2.dp),
            ) {
                Text("${verse.number}", color = Gold, fontSize = 11.sp, fontWeight = FontWeight.Bold)
            }
        }
        Spacer(Modifier.height(6.dp))
        Text(parts.getOrElse(0) { "" }, color = TextDark, fontSize = 15.sp, textAlign = TextAlign.Right, lineHeight = 26.sp, modifier = Modifier.fillMaxWidth())
        if (parts.size > 1) {
            Text(parts[1], color = TextDark, fontSize = 15.sp, textAlign = TextAlign.Right, lineHeight = 26.sp, modifier = Modifier.fillMaxWidth())
        }
    }
}
