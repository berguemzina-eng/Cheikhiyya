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
import androidx.compose.ui.graphics.Brush
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
import com.cheikhiyya.tariqa.ui.theme.IslamicCornerOrnament
import com.cheikhiyya.tariqa.ui.theme.IslamicMedallionBackdrop
import com.cheikhiyya.tariqa.ui.theme.Primary
import com.cheikhiyya.tariqa.ui.theme.PrimaryLight
import com.cheikhiyya.tariqa.ui.theme.ScallopedBottomShape
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
    HomeCard("المسبحة", "tasbih", Icons.Filled.Circle),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onOpenDrawer: () -> Unit, onNavigate: (String) -> Unit) {
    Scaffold(
        containerColor = Cream,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize().navigationBarsPadding()) {
            // ===== Cabecera: silueta festoneada (marco islámico), tamaño equilibrado =====
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(6.dp, ScallopedBottomShape())
                    .background(
                        Brush.verticalGradient(listOf(Primary, PrimaryLight, Primary)),
                        ScallopedBottomShape(),
                    ),
            ) {
                // Resplandor radial dorado detrás del medallón
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .align(Alignment.TopCenter)
                        .background(
                            Brush.radialGradient(
                                colors = listOf(Gold.copy(alpha = 0.20f), Color.Transparent),
                                radius = 380f,
                            ),
                        ),
                )

                // Ornamentos en las esquinas superiores
                IslamicCornerOrnament(modifier = Modifier.size(40.dp).align(Alignment.TopStart).padding(10.dp), rotationDegrees = 0f)
                IslamicCornerOrnament(modifier = Modifier.size(40.dp).align(Alignment.TopEnd).padding(10.dp), rotationDegrees = 90f)

                Column(modifier = Modifier.fillMaxWidth().statusBarsPadding()) {
                    // Barra superior: icono de menú, dentro de la misma superficie verde
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        IconButton(onClick = onOpenDrawer) {
                            Icon(Icons.Filled.Menu, contentDescription = "القائمة", tint = Color.White)
                        }
                        Spacer(Modifier.weight(1f))
                    }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp, bottom = 34.dp, start = 24.dp, end = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    // Medallón: anillos dorados detrás del logo
                    Box(contentAlignment = Alignment.Center) {
                        IslamicMedallionBackdrop(modifier = Modifier.size(126.dp))
                        Box(
                            modifier = Modifier
                                .size(84.dp)
                                .shadow(3.dp, CircleShape)
                                .background(Cream, CircleShape)
                                .border(1.dp, Gold.copy(alpha = 0.6f), CircleShape),
                            contentAlignment = Alignment.Center,
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.logo_circular),
                                contentDescription = "شعار الطريقة الشيخية",
                                modifier = Modifier.size(70.dp),
                            )
                        }
                    }
                    Spacer(Modifier.height(16.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.78f)
                            .shadow(2.dp, RoundedCornerShape(10.dp))
                            .background(Cream, RoundedCornerShape(10.dp))
                            .padding(vertical = 7.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.logo_horizontal),
                            contentDescription = "الطريقة الشيخية الشاذلية",
                            modifier = Modifier.fillMaxWidth(0.92f).height(28.dp),
                        )
                    }
                    Spacer(Modifier.height(8.dp))
                    Text(
                        "( طريقة أسلاف بيضاء نقية )",
                        color = GoldLight,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
                }
            }

            Spacer(Modifier.height(10.dp))

            // ===== Cuadrícula de accesos =====
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(horizontal = 14.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
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
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(1.dp, RoundedCornerShape(14.dp))
            .background(Color.White, RoundedCornerShape(14.dp))
            .border(1.dp, BorderColor, RoundedCornerShape(14.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier.size(32.dp).background(Primary, CircleShape),
            contentAlignment = Alignment.Center,
        ) {
            Icon(card.icon, contentDescription = null, tint = Gold, modifier = Modifier.size(16.dp))
        }
        Spacer(Modifier.width(10.dp))
        Text(
            card.title,
            color = TextDark,
            textAlign = TextAlign.Right,
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.5.sp,
            lineHeight = 16.sp,
            modifier = Modifier.weight(1f),
        )
    }
}
