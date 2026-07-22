package com.cheikhiyya.tariqa.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cheikhiyya.tariqa.ui.theme.*

private data class DinLevel(val title: String, val desc: String, val icon: ImageVector)
private val DIN_LEVELS = listOf(
    DinLevel("الإسلام", "الاستسلام لله ظاهرًا: الشهادة والصلاة والزكاة والصيام والحج", Icons.Filled.CheckCircle),
    DinLevel("الإيمان", "تصديق القلب وطمأنينته بالغيب، مرتبة أعلى من الإسلام", Icons.Filled.Favorite),
    DinLevel("الإحسان", "«أن تعبد الله كأنك تراه» — لبّ الدين وروحه ومقام الصوفي", Icons.Filled.WbSunny),
)

private val PILLARS = listOf(
    "الالتزام بالكتاب والسنة" to "فكل ما خالفهما فباطل مهما نُسب لأهل الطريق",
    "تزكية النفس ومجاهدتها" to "بمحاسبتها والصبر عليها في طاعة الله",
    "الإكثار من الذكر والطاعة" to "فبالذكر يحيا القلب وتصفو الروح",
    "صحبة الصالحين" to "فالمرء يرقى بصحبة أهل الأدب والورع",
    "التحلي بالأخلاق النبوية" to "الصدق والرحمة والتواضع والصفح ولين الجانب",
)

private val NOT_TASSAWOF = listOf(
    "الخرافات والشعوذة",
    "ادعاء الكشف بلا علم",
    "ترك الفرائض",
    "الغلوّ في الأشخاص",
    "البدع المحدثة التي لا أصل لها",
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TassawofScreen(onBack: () -> Unit) {
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
                        Text("التصوف", color = Gold, fontWeight = FontWeight.Bold)
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
        LazyColumn(
            modifier = Modifier.padding(padding).fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
        ) {
            item {
                // مقدمة
                Column(
                    modifier = Modifier.fillMaxWidth().background(Primary, RoundedCornerShape(16.dp)).padding(16.dp),
                ) {
                    Text(
                        "لطالما كان التصوف موضع سؤال عند الناس. والحق أن التصوف كما هو عند أهله الصادقين ليس طقوسًا ولا خروجًا عن الشريعة، بل هو قلب الشريعة المحمدية وروحها، وهو مقام الإحسان في أكمل تجلياته.",
                        color = GoldLight,
                        textAlign = TextAlign.Right,
                        lineHeight = 24.sp,
                        fontSize = 14.sp,
                    )
                }
                Spacer(Modifier.height(20.dp))
                IslamicDivider()
                Spacer(Modifier.height(12.dp))
                SectionTitle("مراتب الدين الثلاث")
                Spacer(Modifier.height(10.dp))
            }

            items(DIN_LEVELS) { level ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)
                        .background(Color.White, RoundedCornerShape(14.dp))
                        .border(1.dp, BorderColor, RoundedCornerShape(14.dp))
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(modifier = Modifier.size(40.dp).background(Primary, CircleShape), contentAlignment = Alignment.Center) {
                        Icon(level.icon, contentDescription = null, tint = Gold, modifier = Modifier.size(20.dp))
                    }
                    Spacer(Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(level.title, color = TextDark, fontWeight = FontWeight.Bold, fontSize = 15.sp, textAlign = TextAlign.Right)
                        Spacer(Modifier.height(2.dp))
                        Text(level.desc, color = TextMuted, fontSize = 12.5.sp, textAlign = TextAlign.Right, lineHeight = 18.sp)
                    }
                }
            }

            item {
                Spacer(Modifier.height(14.dp))
                Text(
                    "فالتصوف إذن هو السعي للوصول إلى مقام الإحسان، بتهذيب الباطن وإحكام الظاهر على نور من الكتاب والسنة — تزكية للنفس، وإحياء للقلب بذكر الله، وصدق في طلب وجه الله.",
                    color = TextDark,
                    textAlign = TextAlign.Right,
                    lineHeight = 24.sp,
                    fontSize = 14.sp,
                    modifier = Modifier.fillMaxWidth(),
                )
                Spacer(Modifier.height(20.dp))
                IslamicDivider()
                Spacer(Modifier.height(12.dp))
                SectionTitle("مرتكزات التصوف الشرعي")
                Spacer(Modifier.height(10.dp))
            }

            itemsIndexed(PILLARS) { index, pair ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
                    verticalAlignment = Alignment.Top,
                ) {
                    Box(modifier = Modifier.size(26.dp).background(Gold, CircleShape), contentAlignment = Alignment.Center) {
                        Text("${index + 1}", color = Primary, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                    Spacer(Modifier.width(10.dp))
                    Column {
                        Text(pair.first, color = TextDark, fontWeight = FontWeight.SemiBold, fontSize = 14.sp, textAlign = TextAlign.Right)
                        Text(pair.second, color = TextMuted, fontSize = 12.sp, textAlign = TextAlign.Right, lineHeight = 18.sp)
                    }
                }
            }

            item {
                Spacer(Modifier.height(14.dp))
                IslamicDivider()
                Spacer(Modifier.height(12.dp))
                SectionTitle("وليس من التصوف")
                Spacer(Modifier.height(10.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFF7ECEC), RoundedCornerShape(14.dp))
                        .border(1.dp, Color(0xFFD9B8B8), RoundedCornerShape(14.dp))
                        .padding(14.dp),
                ) {
                    NOT_TASSAWOF.forEach {
                        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 3.dp)) {
                            Icon(Icons.Filled.Close, contentDescription = null, tint = Color(0xFF8C2F2F), modifier = Modifier.size(14.dp))
                            Spacer(Modifier.width(6.dp))
                            Text(it, color = Color(0xFF6B2A2A), fontSize = 13.sp, textAlign = TextAlign.Right)
                        }
                    }
                }
                Spacer(Modifier.height(20.dp))
                IslamicDivider()
                Spacer(Modifier.height(16.dp))

                // خلاصة
                Column(
                    modifier = Modifier.fillMaxWidth().background(Primary, RoundedCornerShape(16.dp)).padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Icon(Icons.Filled.AutoAwesome, contentDescription = null, tint = Gold, modifier = Modifier.size(22.dp))
                    Spacer(Modifier.height(8.dp))
                    Text(
                        "التصوف هو الإسلام حين يُعاش بقلب محب، والإيمان حين يتزين بالنور، والإحسان حين يثبت في السلوك.\nومن ذاق عرف، ومن عرف صفا، ومن صفا وفى.",
                        color = GoldLight,
                        textAlign = TextAlign.Center,
                        lineHeight = 24.sp,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                    )
                }

                Spacer(Modifier.height(14.dp))
                Text(
                    "بتصرف عن: www.cheikhiyya.com — تعريف التصوف، بحث وتحقيق مقدم الطريقة الشيخية حاكمي مصطفى",
                    color = TextMuted,
                    fontSize = 11.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                )
                Spacer(Modifier.height(24.dp))
            }
        }
    }
}

@Composable
private fun SectionTitle(text: String) {
    Text(
        text,
        color = Primary,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        textAlign = TextAlign.Right,
        modifier = Modifier.fillMaxWidth(),
    )
}
