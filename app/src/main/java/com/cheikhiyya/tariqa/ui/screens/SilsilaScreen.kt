package com.cheikhiyya.tariqa.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cheikhiyya.tariqa.data.SILSILA_CHAIN
import com.cheikhiyya.tariqa.data.SILSILA_SOURCE
import com.cheikhiyya.tariqa.data.SilsilaLink
import com.cheikhiyya.tariqa.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SilsilaScreen(onBack: () -> Unit) {
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
        Column(modifier = Modifier.padding(padding).fillMaxSize()) {
            Row(
                modifier = Modifier.fillMaxWidth().background(GoldLight).padding(vertical = 6.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(Icons.Filled.WifiOff, contentDescription = null, tint = Primary, modifier = Modifier.size(14.dp))
                Spacer(Modifier.width(6.dp))
                Text("سلسلة الطريقة الشيخية البوشيخية — من النبي ﷺ إلى اليوم", color = Primary, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
            }

            LazyColumn(contentPadding = PaddingValues(16.dp)) {
                itemsIndexed(SILSILA_CHAIN) { index, link ->
                    SilsilaRow(index = index, link = link, isLast = index == SILSILA_CHAIN.lastIndex)
                }
                item {
                    Spacer(Modifier.height(10.dp))
                    Text(SILSILA_SOURCE, color = TextMuted, fontSize = 12.sp, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
                    Spacer(Modifier.height(24.dp))
                }
            }
        }
    }
}

@Composable
private fun SilsilaRow(index: Int, link: SilsilaLink, isLast: Boolean) {
    Row(modifier = Modifier.fillMaxWidth()) {
        // خط عمودي متصل + رقم دائري (تسلسل السند)
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.width(36.dp)) {
            Box(
                modifier = Modifier.size(28.dp).background(Primary, CircleShape),
                contentAlignment = Alignment.Center,
            ) {
                Text("${index + 1}", color = Gold, fontSize = 11.sp, fontWeight = FontWeight.Bold)
            }
            if (!isLast) {
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .weight(1f)
                        .defaultMinSize(minHeight = 24.dp)
                        .background(BorderColor),
                )
            }
        }
        Spacer(Modifier.width(10.dp))
        Column(modifier = Modifier.padding(bottom = 18.dp)) {
            Box(
                modifier = Modifier
                    .background(Color.White, RoundedCornerShape(10.dp))
                    .padding(horizontal = 12.dp, vertical = 10.dp),
            ) {
                Column {
                    Text(link.name, color = TextDark, fontWeight = FontWeight.SemiBold, fontSize = 14.sp, textAlign = TextAlign.Right)
                    link.note?.let {
                        Spacer(Modifier.height(4.dp))
                        Text(it, color = Primary, fontSize = 12.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Right)
                    }
                }
            }
        }
    }
}
