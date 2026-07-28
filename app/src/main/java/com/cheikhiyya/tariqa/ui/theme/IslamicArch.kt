package com.cheikhiyya.tariqa.ui.theme

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke

/**
 * Dibuja el contorno de un arco de herradura (mihrab), motivo clásico
 * de la arquitectura islámica, como decoración de fondo sutil detrás
 * de una cabecera. 100% vectorial.
 */
@Composable
fun IslamicArchOutline(
    modifier: Modifier = Modifier,
    color: Color = Gold.copy(alpha = 0.35f),
) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height
        val archTop = h * 0.12f
        val archBottom = h * 0.98f
        val archWidth = w * 0.5f
        val cx = w / 2f
        val left = cx - archWidth / 2f
        val right = cx + archWidth / 2f
        val radius = archWidth / 2f

        val path = Path().apply {
            moveTo(left, archBottom)
            lineTo(left, archTop + radius * 0.55f)
            // Arco de herradura: curva que sobrepasa el semicírculo
            cubicTo(
                left, archTop - radius * 0.25f,
                cx - radius * 0.85f, archTop - radius * 0.55f,
                cx, archTop - radius * 0.55f,
            )
            cubicTo(
                cx + radius * 0.85f, archTop - radius * 0.55f,
                right, archTop - radius * 0.25f,
                right, archTop + radius * 0.55f,
            )
            lineTo(right, archBottom)
        }
        drawPath(path, color = color, style = Stroke(width = 2f))

        // Arco interior, más pequeño, concéntrico
        val inner = 0.72f
        val leftI = cx - (archWidth * inner) / 2f
        val rightI = cx + (archWidth * inner) / 2f
        val radiusI = (archWidth * inner) / 2f
        val archTopI = archTop + (radius - radiusI) * 0.6f
        val pathInner = Path().apply {
            moveTo(leftI, archBottom)
            lineTo(leftI, archTopI + radiusI * 0.55f)
            cubicTo(
                leftI, archTopI - radiusI * 0.25f,
                cx - radiusI * 0.85f, archTopI - radiusI * 0.55f,
                cx, archTopI - radiusI * 0.55f,
            )
            cubicTo(
                cx + radiusI * 0.85f, archTopI - radiusI * 0.55f,
                rightI, archTopI - radiusI * 0.25f,
                rightI, archTopI + radiusI * 0.55f,
            )
            lineTo(rightI, archBottom)
        }
        drawPath(pathInner, color = color.copy(alpha = color.alpha * 0.7f), style = Stroke(width = 1.2f))
    }
}
