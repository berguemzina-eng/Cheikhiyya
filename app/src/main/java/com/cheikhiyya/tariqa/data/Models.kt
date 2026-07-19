package com.cheikhiyya.tariqa.data

/**
 * Un nodo del menú lateral. Puede ser:
 *  - una categoría con hijos (children != null), que se despliega/pliega
 *  - un enlace a un artículo nativo (articleId != null)
 * Todo el contenido vive en este mismo proceso: no hay llamadas de red.
 */
data class MenuNode(
    val id: String,
    val title: String,
    val icon: String? = null,
    val articleId: String? = null,
    val children: List<MenuNode>? = null,
)

/**
 * Un artículo con contenido 100% nativo y offline.
 * imageRes: nombre de un drawable que tú añadas en res/drawable (opcional).
 * audioRes: nombre de un archivo en res/raw (opcional, para awrad con audio).
 */
data class Article(
    val id: String,
    val title: String,
    val body: List<String>, // párrafos
    val imageRes: String? = null,
    val audioRes: String? = null,
    val source: String? = null,
)
