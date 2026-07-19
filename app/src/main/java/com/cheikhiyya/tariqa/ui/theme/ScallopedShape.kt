package com.cheikhiyya.tariqa.ui.theme

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

/**
 * Rectángulo con esquinas superiores redondeadas y el borde INFERIOR
 * festoneado (una serie de arcos), como los cortinajes y marcos
 * decorativos típicos de la arquitectura islámica (muqarnas
 * simplificados). Mucho más reconocible como "diseño islámico" que
 * un simple rectángulo con líneas encima.
 */
class ScallopedBottomShape(
    private val scallops: Int = 7,
    private val scallopDepth: Dp = 16.dp,
    private val topCornerRadius: Dp = 26.dp,
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density,
    ): Outline {
        val r = with(density) { topCornerRadius.toPx() }
        val depth = with(density) { scallopDepth.toPx() }
        val w = size.width
        val straightBottom = size.height - depth

        val path = Path().apply {
            moveTo(0f, r)
            quadraticBezierTo(0f, 0f, r, 0f)
            lineTo(w - r, 0f)
            quadraticBezierTo(w, 0f, w, r)
            lineTo(w, straightBottom)

            val segment = w / scallops
            for (i in 0 until scallops) {
                val startX = w - i * segment
                val endX = w - (i + 1) * segment
                val midX = (startX + endX) / 2f
                quadraticBezierTo(midX, size.height + depth * 0.55f, endX, straightBottom)
            }
            close()
        }
        return Outline.Generic(path)
    }
}
