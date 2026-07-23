package com.cheikhiyya.tariqa.ui.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.BookmarkAdded
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cheikhiyya.tariqa.R
import com.cheikhiyya.tariqa.data.*
import com.cheikhiyya.tariqa.ui.theme.*
import kotlinx.coroutines.launch

private const val PREFS_NAME = "cheikhiyya_prefs"
private const val KEY_YAQOUTA_VERSE = "yaqouta_last_verse_number" // رقم البيت (1..178)، أو -1 إن لم يُحفظ شيء

// عدد العناصر الثابتة قبل أبيات القصيدة في القائمة (المقدمة + أذكار الافتتاح)
private val ITEMS_BEFORE_VERSES = 1 + YAQOUTA_OPENING.size

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun YaqoutaScreen(onBack: () -> Unit) {
    val context = LocalContext.current
    val prefs = remember { context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE) }
    var savedVerseNumber by remember { mutableIntStateOf(prefs.getInt(KEY_YAQOUTA_VERSE, -1)) }
    var showResumeBanner by remember { mutableStateOf(savedVerseNumber in 1..YAQOUTA_VERSES.size) }

    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    fun saveCurrentPosition() {
        // نحدد أول بيت ظاهر حاليا على الشاشة، ونحفظ رقمه
        val firstVisible = listState.firstVisibleItemIndex
        val verseIndex = firstVisible - ITEMS_BEFORE_VERSES
        val verseNumber = if (verseIndex in YAQOUTA_VERSES.indices) YAQOUTA_VERSES[verseIndex].number else 1
        prefs.edit().putInt(KEY_YAQOUTA_VERSE, verseNumber).apply()
        savedVerseNumber = verseNumber
        showResumeBanner = false
    }

    fun resumeAtSavedVerse() {
        val verseIndex = YAQOUTA_VERSES.indexOfFirst { it.number == savedVerseNumber }
        if (verseIndex >= 0) {
            scope.launch {
                listState.animateScrollToItem(ITEMS_BEFORE_VERSES + verseIndex)
            }
        }
        showResumeBanner = false
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(modifier = Modifier.size(30.dp).background(Cream, CircleShape), contentAlignment = Alignment.Center) {
                            androidx.compose.foundation.Image(painter = painterResource(id = R.drawable.logo_circular), contentDescription = null, modifier = Modifier.size(24.dp))
                        }
                        Spacer(Modifier.width(8.dp))
                        Text("الياقوتة", color = Gold, fontWeight = FontWeight.Bold)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "رجوع", tint = Color.White)
                    }
                },
                actions = {
                    IconButton(onClick = { saveCurrentPosition() }) {
                        Icon(
                            if (savedVerseNumber > 0) Icons.Filled.BookmarkAdded else Icons.Filled.BookmarkBorder,
                            contentDescription = "احفظ موضعي",
                            tint = Color.White,
                        )
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

            if (showResumeBanner) {
                ResumeBanner(
                    verseNumber = savedVerseNumber,
                    onResume = { resumeAtSavedVerse() },
                    onDismiss = { showResumeBanner = false },
                )
            }

            LazyColumn(state = listState, contentPadding = PaddingValues(16.dp)) {
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

                itemsIndexed(YAQOUTA_VERSES) { index, verse ->
                    YaqoutaVerseCard(
                        verse = verse,
                        isBookmarked = verse.number == savedVerseNumber,
                        onBookmarkClick = {
                            prefs.edit().putInt(KEY_YAQOUTA_VERSE, verse.number).apply()
                            savedVerseNumber = verse.number
                            showResumeBanner = false
                        },
                    )
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
private fun ResumeBanner(verseNumber: Int, onResume: () -> Unit, onDismiss: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .background(Color(0xFFF1E9D2), RoundedCornerShape(12.dp))
            .border(1.dp, Gold, RoundedCornerShape(12.dp))
            .clickable { onResume() }
            .padding(horizontal = 14.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(Icons.Filled.BookmarkAdded, contentDescription = null, tint = Primary, modifier = Modifier.size(20.dp))
        Spacer(Modifier.width(10.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text("تابع من حيث توقفت", color = Primary, fontWeight = FontWeight.Bold, fontSize = 13.sp, textAlign = TextAlign.Right)
            Text("آخر موضع محفوظ: البيت رقم $verseNumber", color = TextMuted, fontSize = 11.sp, textAlign = TextAlign.Right)
        }
        TextButton(onClick = onDismiss) {
            Text("إغلاق", color = TextMuted, fontSize = 12.sp)
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
private fun YaqoutaVerseCard(verse: YaqoutaVerse, isBookmarked: Boolean, onBookmarkClick: () -> Unit) {
    val parts = verse.text.split(" == ")
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(if (isBookmarked) Color(0xFFF1E9D2) else Color.White, RoundedCornerShape(10.dp))
            .border(1.dp, if (isBookmarked) Gold else BorderColor, RoundedCornerShape(10.dp)),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                parts.getOrElse(0) { "" },
                color = TextDark,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                lineHeight = 30.sp,
                modifier = Modifier.fillMaxWidth(),
            )
            if (parts.size > 1) {
                Spacer(Modifier.height(4.dp))
                Text(
                    parts[1],
                    color = TextDark,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center,
                    lineHeight = 30.sp,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
        Row(
            modifier = Modifier.align(Alignment.TopEnd).padding(6.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .clickable(onClick = onBookmarkClick)
                    .padding(4.dp),
            ) {
                Icon(
                    if (isBookmarked) Icons.Filled.BookmarkAdded else Icons.Filled.BookmarkBorder,
                    contentDescription = "احفظ موضعك هنا",
                    tint = if (isBookmarked) Gold else TextMuted,
                    modifier = Modifier.size(16.dp),
                )
            }
            Spacer(Modifier.width(2.dp))
            Box(
                modifier = Modifier
                    .background(Primary, RoundedCornerShape(20.dp))
                    .padding(horizontal = 8.dp, vertical = 2.dp),
            ) {
                Text("${verse.number}", color = Gold, fontSize = 11.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}
