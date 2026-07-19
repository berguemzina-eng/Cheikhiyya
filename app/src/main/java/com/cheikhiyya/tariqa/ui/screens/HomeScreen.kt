package com.cheikhiyya.tariqa.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.cheikhiyya.tariqa.R
import com.cheikhiyya.tariqa.ui.theme.BorderColor
import com.cheikhiyya.tariqa.ui.theme.Cream
import com.cheikhiyya.tariqa.ui.theme.Gold
import com.cheikhiyya.tariqa.ui.theme.GoldLight
import com.cheikhiyya.tariqa.ui.theme.IslamicPatternBackground
import com.cheikhiyya.tariqa.ui.theme.Primary
import com.cheikhiyya.tariqa.ui.theme.PrimaryLight

data class HomeCard(val title: String, val destination: String)

private val HOME_CARDS = listOf(
    HomeCard("الشيخ الحالي للطريقة", "sidi_hamza"),
    HomeCard("التصوف", "tassawof"),
    HomeCard("الطريقة الشيخية", "tariqa_cheikhiyya"),
    HomeCard("شروط الانتساب", "tariqa_conditions"),
    HomeCard("حزب الفلاح", "hizb_al_falah"),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onOpenDrawer: () -> Unit, onNavigate: (String) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("الطريقة الشيخية", color = Gold, fontWeight = FontWeight.Bold) },
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
            // Cabecera con el logo y patrón geométrico islámico de fondo
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(Primary, RoundedCornerShape(18.dp))
                    .border(1.dp, Gold.copy(alpha = 0.6f), RoundedCornerShape(18.dp)),
            ) {
                IslamicPatternBackground(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(190.dp),
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 22.dp, horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo_placeholder),
                        contentDescription = "شعار الطريقة الشيخية",
                        modifier = Modifier.size(76.dp),
                    )
                    Spacer(Modifier.height(10.dp))
                    Text(
                        "موقع الطريقة الشيخية الشاذلية",
                        color = GoldLight,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    Text(
                        "( طريقة أسلاف بيضاء نقية )",
                        color = GoldLight,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(HOME_CARDS) { card ->
                    Box(
                        modifier = Modifier
                            .height(110.dp)
                            .fillMaxWidth()
                            .background(PrimaryLight, RoundedCornerShape(12.dp))
                            .border(1.dp, BorderColor, RoundedCornerShape(12.dp))
                            .clickable { onNavigate(card.destination) }
                            .padding(12.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            card.title,
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.SemiBold,
                        )
                    }
                }
            }
        }
    }
}
