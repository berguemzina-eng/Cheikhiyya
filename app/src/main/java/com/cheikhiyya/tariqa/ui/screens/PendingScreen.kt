package com.cheikhiyya.tariqa.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Construction
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.cheikhiyya.tariqa.ui.theme.Cream
import com.cheikhiyya.tariqa.ui.theme.Gold
import com.cheikhiyya.tariqa.ui.theme.Primary
import com.cheikhiyya.tariqa.ui.theme.TextMuted

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PendingScreen(title: String, onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title, color = Gold, fontWeight = FontWeight.Bold) },
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
            modifier = Modifier.padding(padding).fillMaxSize().padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Icon(Icons.Filled.Construction, contentDescription = null, tint = TextMuted, modifier = Modifier.size(48.dp))
            Spacer(Modifier.height(16.dp))
            Text(
                "لم يُضَف محتوى «$title» بعد إلى النسخة غير المتصلة من التطبيق.",
                textAlign = TextAlign.Center,
                color = TextMuted,
            )
        }
    }
}
