package com.cheikhiyya.tariqa.data

// Árbol de navegación, reorganizado para reflejar el ORDEN Y AGRUPACIÓN
// REALES del menú de www.cheikhiyya.com (verificado con capturas de
// pantalla de la web real). Las entradas marcadas con pending(...)
// son categorías o páginas confirmadas que existen en la web pero
// para las que aún no tengo el texto exacto — se completan pegando
// el texto real de esa página (ver conversación).

private fun pending(title: String) = "pending:$title"

val MENU: List<MenuNode> = listOf(
    MenuNode(id = "home", title = "الرئيسية", icon = "home", articleId = "home"),
    MenuNode(id = "tassawof", title = "التصوف", icon = "book", articleId = "tassawof"),
    MenuNode(id = "tariqa", title = "الطريقة الشيخية", icon = "star", articleId = "tariqa_cheikhiyya"),
    MenuNode(id = "baya_adab", title = "العهد والبيعة وآداب الذكر", icon = "hand", articleId = "baya_adab"),
    MenuNode(id = "conditions", title = "شروط الانتساب", icon = "check", articleId = "tariqa_conditions"),

    // ===== أشهر الزوايا (نفس ترتيب الموقع الحقيقي بالضبط) =====
    MenuNode(
        id = "zawiyas",
        title = "أشهر الزوايا",
        icon = "place",
        children = listOf(
            MenuNode(
                id = "zawiyas_algeria",
                title = "الزوايا بالجزائر",
                children = listOf(
                    MenuNode(id = "zawiya_oued_namous", title = "زاوية واد الناموس", articleId = "zawiya_oued_namous"),
                    MenuNode(id = "zawiya_markaziya", title = "الزاوية المركزية", articleId = "zawiya_markaziya"),
                    MenuNode(id = "zawiya_brizina", title = "زاوية بريزينة", articleId = "zawiya_brizina"),
                    MenuNode(id = "zawiya_ain_skhouna", title = "زاوية عين سخونة", articleId = "zawiya_ain_skhouna"),
                    MenuNode(id = "zawiya_matlili", title = "زاوية متليلي", articleId = "zawiya_matlili"),
                    MenuNode(id = "zawiya_3asla", title = "زاوية عسلة", articleId = "zawiya_3asla"),
                    MenuNode(id = "zawiya_z_bouamama", title = "ز ش بوعمامة", articleId = pending("ز ش بوعمامة")),
                    MenuNode(id = "zawiya_sidi_taj", title = "زاوية سيدي التاج", articleId = pending("زاوية سيدي التاج")),
                    MenuNode(id = "zawiya_tlemcen", title = "زاوية تلمسان", articleId = "zawiya_tlemcen"),
                    MenuNode(id = "zawiya_mowahidin", title = "زاوية الموحدين", articleId = "zawiya_mowahidin"),
                    MenuNode(id = "masjid_karama", title = "مسجد الكرامة", articleId = pending("مسجد الكرامة")),
                    MenuNode(id = "zawiya_asmahiyya", title = "الزاوية السماحية", articleId = "zawiya_asmahiyya"),
                    MenuNode(id = "zawiya_bni_wanif", title = "زاوية بني ونيف", articleId = "zawiya_bni_wanif"),
                    MenuNode(id = "zawiya_boynan", title = "زاوية بوينان البليدة", articleId = "zawiya_boynan"),
                    MenuNode(id = "zawiya_m3askar", title = "زاوية معسكر", articleId = "zawiya_m3askar"),
                ),
            ),
            MenuNode(
                id = "zawiyas_morocco",
                title = "الزوايا بالمغرب",
                children = listOf(
                    MenuNode(id = "zawiya_bouamamiya_1", title = "الزاوية البوعمامية", articleId = "zawiya_bouamamiya"),
                    MenuNode(id = "zawiya_fguig", title = "زاوية فجيج", articleId = pending("زاوية فجيج")),
                    MenuNode(id = "zawiya_sidi_mohamed", title = "زاوية سيدي محمد عبد الله", articleId = "zawiya_sidi_mohamed"),
                    MenuNode(id = "zawiya_sidi_brahim", title = "زاوية سيدي الحاج إبراهيم", articleId = "zawiya_sidi_brahim"),
                    MenuNode(id = "zawiya_bouamamiya_2", title = "الزاوية البوعمامية الشيخية", articleId = pending("الزاوية البوعمامية الشيخية")),
                    MenuNode(id = "jama3_sidi_ahmed", title = "جامع سيدي احمد", articleId = pending("جامع سيدي احمد")),
                    MenuNode(id = "zawiya_oujda", title = "زاوية وجدة", articleId = "zawiya_oujda"),
                    MenuNode(id = "zawiya_bouarfa", title = "زاوية بوعرفة", articleId = pending("زاوية بوعرفة")),
                ),
            ),
        ),
    ),

    MenuNode(id = "silsila", title = "سلسلة الطريقة 1", icon = "chart", articleId = "silsila"),
    MenuNode(id = "silsila2", title = "سلسلة الطريقة 2 (مفصلة)", icon = "chart", articleId = "silsila2"),

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

    MenuNode(id = "golden_book", title = "الكتاب الذهبي", icon = "book", articleId = pending("الكتاب الذهبي")),

    MenuNode(
        id = "museum",
        title = "المتحف",
        icon = "place",
        children = listOf(
            MenuNode(id = "museum_kotob", title = "كتب", articleId = pending("المتحف - كتب")),
            MenuNode(id = "museum_rasail", title = "رسائل", articleId = pending("المتحف - رسائل")),
        ),
    ),

    MenuNode(id = "contact", title = "إتصل بنا", icon = "mail", articleId = "contact"),
)
