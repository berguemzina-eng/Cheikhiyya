package com.cheikhiyya.tariqa.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.HourglassTop
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
fun PendingScreen(title: String, onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(modifier = Modifier.size(30.dp).background(Cream, CircleShape), contentAlignment = Alignment.Center) {
                            Image(painter = painterResource(id = R.drawable.logo_circular), contentDescription = null, modifier = Modifier.size(24.dp))
                        }
                        Spacer(Modifier.width(8.dp))
                        Text(title, color = Gold, fontWeight = FontWeight.Bold)
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
            modifier = Modifier.padding(padding).fillMaxSize().padding(28.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            // شارة دائرية بنفس أسلوب باقي التطبيق (خلفية خضراء، أيقونة ذهبية)
            Box(
                modifier = Modifier
                    .size(84.dp)
                    .background(Primary, CircleShape)
                    .border(1.5.dp, Gold.copy(alpha = 0.6f), CircleShape),
                contentAlignment = Alignment.Center,
            ) {
                Icon(Icons.Filled.HourglassTop, contentDescription = null, tint = Gold, modifier = Modifier.size(36.dp))
            }

            Spacer(Modifier.height(24.dp))

            Text(
                title,
                color = TextDark,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp,
                textAlign = TextAlign.Center,
            )

            Spacer(Modifier.height(10.dp))

            IslamicDivider(modifier = Modifier.fillMaxWidth(0.6f))

            Spacer(Modifier.height(14.dp))

            Text(
                "لم يُضَف محتوى هذا القسم بعد إلى النسخة غير المتصلة من التطبيق.",
                textAlign = TextAlign.Center,
                color = TextMuted,
                fontSize = 14.sp,
                lineHeight = 22.sp,
            )

            Spacer(Modifier.height(6.dp))

            Text(
                "سيُضاف قريبا بإذن الله",
                textAlign = TextAlign.Center,
                color = Primary,
                fontWeight = FontWeight.SemiBold,
                fontSize = 13.sp,
            )
        }
    }
}
