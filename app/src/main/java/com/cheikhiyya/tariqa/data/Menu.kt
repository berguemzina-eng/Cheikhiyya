package com.cheikhiyya.tariqa.data

// Árbol de navegación completo, reflejando la estructura real de
// www.cheikhiyya.com. Todas las entradas de contenido textual ya
// apuntan a artículos nativos reales (ver Articles.kt, Hadra.kt,
// Yaqouta.kt, HizbAlFalah.kt). Solo las galerías de fotos siguen
// como "pending" porque requieren imágenes reales que debes subir tú
// (ver README) — no hay ningún otro contenido pendiente.

private fun pending(title: String) = "pending:$title"

val MENU: List<MenuNode> = listOf(
    MenuNode(id = "home", title = "الرئيسية", icon = "home", articleId = "home"),
    MenuNode(id = "tassawof", title = "التصوف", icon = "book", articleId = "tassawof"),
    MenuNode(id = "tariqa", title = "الطريقة الشيخية", icon = "star", articleId = "tariqa_cheikhiyya"),
    MenuNode(id = "conditions", title = "شروط الانتساب", icon = "check", articleId = "tariqa_conditions"),
    MenuNode(
        id = "cheikhs",
        title = "شيوخ الطريقة",
        icon = "person",
        children = listOf(
            MenuNode(id = "sidi_hamza", title = "الشيخ الحالي", articleId = "sidi_hamza"),
            MenuNode(id = "sidi_abdelkader", title = "سيدي عبد القادر بن محمد (المؤسس)", articleId = "sidi_abdelkader"),
            MenuNode(id = "sidi_bouamama", title = "سيدي بوعمامة", articleId = "sidi_bouamama"),
            MenuNode(id = "sidi_tayeb", title = "سيدي الحاج الطيب", articleId = "sidi_tayeb"),
            MenuNode(id = "sidi_abdelhakem", title = "سيدي عبد الحاكم", articleId = "sidi_abdelhakem"),
            MenuNode(id = "sidi_ahmed_ben_cheikh", title = "سيدي أحمد بن الشيخ", articleId = "sidi_ahmed_ben_cheikh"),
        ),
    ),
    MenuNode(
        id = "zawiyas",
        title = "الزوايا",
        icon = "place",
        children = listOf(
            MenuNode(id = "zawiya_markaziya", title = "الزاوية المركزية (الجزائر)", articleId = "zawiya_markaziya"),
            MenuNode(id = "zawiya_bouamamiya", title = "الزاوية البوعمامية (المغرب)", articleId = "zawiya_bouamamiya"),
        ),
    ),
    MenuNode(
        id = "adkar",
        title = "الأذكار والأوراد",
        icon = "hand",
        children = listOf(
            MenuNode(id = "tasbih", title = "المسبحة", articleId = "tasbih"),
            MenuNode(id = "hizb_al_falah", title = "حزب الفلاح", articleId = "hizb_al_falah"),
            MenuNode(id = "hadra", title = "الحضرة", articleId = "hadra"),
            MenuNode(id = "yaqouta", title = "الياقوتة", articleId = "yaqouta"),
            MenuNode(id = "adkar_amma", title = "الأذكار العامة", articleId = "adkar_amma"),
        ),
    ),
    MenuNode(
        id = "photos",
        title = "الصور",
        icon = "image",
        children = listOf(
            MenuNode(id = "photos_cheikhs", title = "شيوخ الطريقة", articleId = pending("صور شيوخ الطريقة")),
            MenuNode(id = "photos_zaouia", title = "زوايا الطريقة", articleId = pending("صور زوايا الطريقة")),
        ),
    ),
    MenuNode(id = "contact", title = "إتصل بنا", icon = "mail", articleId = "contact"),
)
