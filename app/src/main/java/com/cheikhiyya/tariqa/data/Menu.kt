package com.cheikhiyya.tariqa.data

// Árbol de navegación completo, reflejando la estructura real de
// www.cheikhiyya.com. Las entradas con articleId="hizb_al_falah" o que
// apuntan a un id presente en ALL_ARTICLES ya tienen contenido nativo
// completo. El resto usa articleId = "pending:<Título>" y muestra una
// pantalla explicando que ese contenido aún no se ha añadido — así el
// menú está completo desde ya, y se va rellenando poco a poco sin tocar
// la navegación.

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
            MenuNode(id = "sidi_abdelkader", title = "سيدي عبد القادر بن محمد (المؤسس)", articleId = pending("سيدي عبد القادر بن محمد")),
            MenuNode(id = "sidi_bouamama", title = "سيدي بوعمامة", articleId = pending("سيدي بوعمامة")),
            MenuNode(id = "sidi_tayeb", title = "سيدي الحاج الطيب", articleId = pending("سيدي الحاج الطيب")),
            MenuNode(id = "sidi_abdelhakem", title = "سيدي عبد الحاكم", articleId = pending("سيدي عبد الحاكم")),
            MenuNode(id = "sidi_ahmed_ben_cheikh", title = "سيدي أحمد بن الشيخ", articleId = pending("سيدي أحمد بن الشيخ")),
        ),
    ),
    MenuNode(
        id = "zawiyas",
        title = "الزوايا",
        icon = "place",
        children = listOf(
            MenuNode(id = "zawiya_markaziya", title = "الزاوية المركزية (الجزائر)", articleId = pending("الزاوية المركزية")),
            MenuNode(id = "zawiya_bouamamiya", title = "الزاوية البوعمامية (المغرب)", articleId = pending("الزاوية البوعمامية")),
            MenuNode(id = "zawiya_paris", title = "زاوية باريس", articleId = pending("زاوية باريس")),
        ),
    ),
    MenuNode(
        id = "adkar",
        title = "الأذكار والأوراد",
        icon = "hand",
        children = listOf(
            MenuNode(id = "hizb_al_falah", title = "حزب الفلاح", articleId = "hizb_al_falah"),
            MenuNode(id = "hadra", title = "الحضرة", articleId = pending("الحضرة")),
            MenuNode(id = "yaqouta", title = "الياقوتة", articleId = pending("الياقوتة")),
            MenuNode(id = "adkar_amma", title = "الأذكار العامة", articleId = pending("الأذكار العامة")),
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
    MenuNode(id = "contact", title = "إتصل بنا", icon = "mail", articleId = pending("إتصل بنا")),
)
