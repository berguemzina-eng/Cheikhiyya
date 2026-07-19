package com.cheikhiyya.tariqa.ui.theme

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import kotlin.math.cos
import kotlin.math.sin

/**
 * Medallón central de anillos concéntricos con una estrella de 16 puntas,
 * como los "unwan" (frontispicios ornamentales) de los manuscritos
 * islámicos clásicos. Pensado para ir detrás del logo, centrado.
 */
@Composable
fun IslamicMedallionBackdrop(
    modifier: Modifier = Modifier,
    color: Color = Gold.copy(alpha = 0.5f),
) {
    Canvas(modifier = modifier) {
        val cx = size.width / 2f
        val cy = size.height / 2f
        val maxRadius = minOf(size.width, size.height) / 2f

        // Anillos concéntricos
        listOf(0.98f, 0.82f).forEach { ratio ->
            drawCircle(color = color.copy(alpha = color.alpha * 0.6f), radius = maxRadius * ratio, center = Offset(cx, cy), style = Stroke(width = 1.2f))
        }

        // Estrella de 16 puntas, muy abierta, como marco
        val points = 16
        val outerR = maxRadius * 0.98f
        val innerR = maxRadius * 0.88f
        val path = Path()
        for (i in 0 until points * 2) {
            val angle = (Math.PI * i / points) - Math.PI / 2
            val r = if (i % 2 == 0) outerR else innerR
            val x = cx + (r * cos(angle)).toFloat()
            val y = cy + (r * sin(angle)).toFloat()
            if (i == 0) path.moveTo(x, y) else path.lineTo(x, y)
        }
        path.close()
        drawPath(path, color = color, style = Stroke(width = 1.4f))
    }
}

/**
 * Pequeño ornamento tipo arabesco para una esquina (un cuarto de roseta
 * con un tallo curvo), muy usado en los márgenes de manuscritos.
 * rotationDegrees permite reutilizarlo girado en las 4 esquinas.
 */
@Composable
fun IslamicCornerOrnament(
    modifier: Modifier = Modifier,
    color: Color = Gold.copy(alpha = 0.55f),
    rotationDegrees: Float = 0f,
) {
    Canvas(modifier = modifier) {
        rotate(rotationDegrees) {
            val w = size.width
            val h = size.height
            val path = Path().apply {
                moveTo(0f, h * 0.55f)
                quadraticBezierTo(w * 0.15f, h * 0.15f, w * 0.55f, 0f)
            }
            drawPath(path, color = color, style = Stroke(width = 1.6f))
            // pequeña roseta en la punta
            val tipX = w * 0.55f
            val tipY = h * 0.02f
            drawCircle(color = color, radius = w * 0.05f, center = Offset(tipX, tipY), style = Stroke(width = 1.2f))
            drawCircle(color = color, radius = w * 0.018f, center = Offset(tipX, tipY))
        }
    }
}
