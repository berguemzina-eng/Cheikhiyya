package com.cheikhiyya.tariqa.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cheikhiyya.tariqa.R
import com.cheikhiyya.tariqa.ui.theme.BorderColor
import com.cheikhiyya.tariqa.ui.theme.Cream
import com.cheikhiyya.tariqa.ui.theme.Gold
import com.cheikhiyya.tariqa.ui.theme.GoldLight
import com.cheikhiyya.tariqa.ui.theme.IslamicArchOutline
import com.cheikhiyya.tariqa.ui.theme.IslamicPatternBackground
import com.cheikhiyya.tariqa.ui.theme.Primary
import com.cheikhiyya.tariqa.ui.theme.TextDark

data class HomeCard(val title: String, val destination: String, val icon: ImageVector)

private val HOME_CARDS = listOf(
    HomeCard("الشيخ الحالي للطريقة", "sidi_hamza", Icons.Filled.Person),
    HomeCard("التصوف", "tassawof", Icons.Filled.MenuBook),
    HomeCard("الطريقة الشيخية", "tariqa_cheikhiyya", Icons.Filled.Star),
    HomeCard("شروط الانتساب", "tariqa_conditions", Icons.Filled.CheckCircle),
    HomeCard("حزب الفلاح", "hizb_al_falah", Icons.Filled.VolunteerActivism),
    HomeCard("الحضرة", "hadra", Icons.Filled.NightsStay),
    HomeCard("الياقوتة", "yaqouta", Icons.Filled.AutoAwesome),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onOpenDrawer: () -> Unit, onNavigate: (String) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier.size(32.dp).background(Cream, CircleShape),
                            contentAlignment = Alignment.Center,
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.logo_circular),
                                contentDescription = null,
                                modifier = Modifier.size(26.dp),
                            )
                        }
                        Spacer(Modifier.width(8.dp))
                        Text("الطريقة الشيخية", color = Gold, fontWeight = FontWeight.Bold)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onOpenDrawer) {
                        Icon(Icons.Filled.Menu, contentDescription = "القائمة", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Primary),
            )
        },
        containerColor = Cream,
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize()) {
            // ===== Cabecera compacta =====
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp)
                    .shadow(3.dp, RoundedCornerShape(20.dp))
                    .background(
                        androidx.compose.ui.graphics.Brush.verticalGradient(
                            listOf(Primary, PrimaryLight),
                        ),
                        RoundedCornerShape(20.dp),
                    )
                    .border(1.dp, Gold.copy(alpha = 0.4f), RoundedCornerShape(20.dp)),
            ) {
                IslamicPatternBackground(
                    modifier = Modifier.matchParentSize(),
                    lineColor = Gold.copy(alpha = 0.08f),
                )
                IslamicArchOutline(
                    modifier = Modifier.matchParentSize(),
                    color = Gold.copy(alpha = 0.22f),
                )

                Column(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp, horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Box(
                        modifier = Modifier.size(52.dp).background(Cream, CircleShape),
                        contentAlignment = Alignment.Center,
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.logo_circular),
                            contentDescription = "شعار الطريقة الشيخية",
                            modifier = Modifier.size(43.dp),
                        )
                    }
                    Spacer(Modifier.height(8.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.72f)
                            .background(Cream, RoundedCornerShape(8.dp))
                            .padding(vertical = 5.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.logo_horizontal),
                            contentDescription = "الطريقة الشيخية الشاذلية",
                            modifier = Modifier.fillMaxWidth(0.9f).height(22.dp),
                        )
                    }
                    Spacer(Modifier.height(4.dp))
                    Text(
                        "( طريقة أسلاف بيضاء نقية )",
                        color = GoldLight,
                        fontSize = 11.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            }

            // ===== Cuadrícula de accesos =====
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(horizontal = 14.dp, vertical = 6.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(HOME_CARDS) { card ->
                    HomeCardItem(card) { onNavigate(card.destination) }
                }
            }
        }
    }
}

@Composable
private fun HomeCardItem(card: HomeCard, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(118.dp)
            .shadow(1.dp, RoundedCornerShape(18.dp))
            .background(Color.White, RoundedCornerShape(18.dp))
            .border(1.dp, BorderColor, RoundedCornerShape(18.dp))
            .clickable(onClick = onClick)
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .background(Primary, CircleShape),
            contentAlignment = Alignment.Center,
        ) {
            Icon(card.icon, contentDescription = null, tint = Gold, modifier = Modifier.size(18.dp))
        }
        Spacer(Modifier.height(8.dp))
        Text(
            card.title,
            color = TextDark,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.5.sp,
            lineHeight = 16.sp,
        )
    }
}
