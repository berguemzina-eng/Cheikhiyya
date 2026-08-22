package com.cheikhiyya.tariqa.ui.theme

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.sin

/**
 * Separador decorativo: línea — roseta de 8 puntas — línea.
 * Muy usado en los márgenes de manuscritos y ediciones religiosas
 * islámicas para marcar el final de una sección o capítulo.
 */
@Composable
fun IslamicDivider(
    modifier: Modifier = Modifier,
    color: Color = Gold,
) {
    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(24.dp)
            .padding(horizontal = 24.dp),
    ) {
        val midY = size.height / 2f
        val starRadius = size.height * 0.42f
        val cx = size.width / 2f
        val gap = starRadius * 1.6f

        drawLine(color, Offset(0f, midY), Offset(cx - gap, midY), strokeWidth = 1.4f)
        drawLine(color, Offset(cx + gap, midY), Offset(size.width, midY), strokeWidth = 1.4f)

        val points = 8
        val innerRatio = 0.45f
        val path = androidx.compose.ui.graphics.Path()
        for (i in 0 until points * 2) {
            val angle = (Math.PI * i / points) - Math.PI / 2
            val r = if (i % 2 == 0) starRadius else starRadius * innerRatio
            val x = cx + (r * cos(angle)).toFloat()
            val y = midY + (r * sin(angle)).toFloat()
            if (i == 0) path.moveTo(x, y) else path.lineTo(x, y)
        }
        path.close()
        drawPath(path, color = color, style = Stroke(width = 1.4f))
    }
}
