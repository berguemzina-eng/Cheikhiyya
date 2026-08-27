package com.cheikhiyya.tariqa.ui.theme

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import kotlin.math.cos
import kotlin.math.sin

/**
 * Dibuja un patrón geométrico islámico clásico (estrellas de 8 puntas,
 * "khatam" / girih) como textura de fondo. 100% vectorial, generado
 * por código: no depende de ninguna imagen externa.
 */
@Composable
fun IslamicPatternBackground(
    modifier: Modifier = Modifier,
    lineColor: Color = Gold.copy(alpha = 0.16f),
    tileSize: Float = 64f,
) {
    Canvas(modifier = modifier) {
        val cols = (size.width / tileSize).toInt() + 2
        val rows = (size.height / tileSize).toInt() + 2
        for (row in -1 until rows) {
            for (col in -1 until cols) {
                val cx = col * tileSize
                val cy = row * tileSize
                drawEightPointStar(
                    center = Offset(cx, cy),
                    radius = tileSize * 0.46f,
                    color = lineColor,
                )
            }
        }
    }
}

private fun androidx.compose.ui.graphics.drawscope.DrawScope.drawEightPointStar(
    center: Offset,
    radius: Float,
    color: Color,
) {
    val path = Path()
    val points = 8
    val innerRatio = 0.5f
    for (i in 0 until points * 2) {
        val angle = (Math.PI * i / points) - Math.PI / 2
        val r = if (i % 2 == 0) radius else radius * innerRatio
        val x = center.x + (r * cos(angle)).toFloat()
        val y = center.y + (r * sin(angle)).toFloat()
        if (i == 0) path.moveTo(x, y) else path.lineTo(x, y)
    }
    path.close()
    drawPath(path, color = color, style = Stroke(width = 1.2f))
}
