package com.cheikhiyya.tariqa.ui.theme

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import kotlin.math.cos
import kotlin.math.sin

/**
 * "شجرة السلسلة" — un árbol estilizado con ramas doradas y nodos verdes
 * en las puntas, motivo tradicional de las cadenas de transmisión
 * espiritual (سند) en los manuscritos sufíes. Diseño 100% vectorial y
 * original, en el mismo lenguaje visual del resto de la app.
 */
@Composable
fun SilsilaTreeMotif(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height
        val baseX = w / 2f
        val baseY = h * 0.98f
        val trunkTopY = h * 0.58f

        // الجذع
        val trunk = Path().apply {
            moveTo(baseX, baseY)
            lineTo(baseX, trunkTopY)
        }
        drawPath(trunk, color = Gold, style = Stroke(width = w * 0.045f))

        // الأغصان: 5 أغصان متفرعة من قمة الجذع، بزوايا متدرجة
        val branchAngles = listOf(-70f, -35f, 0f, 35f, 70f) // بالدرجات، 0 = للأعلى مباشرة
        val branchLength = h * 0.42f

        branchAngles.forEach { angleDeg ->
            val angleRad = Math.toRadians((angleDeg - 90).toDouble())
            val endX = baseX + (branchLength * cos(angleRad)).toFloat()
            val endY = trunkTopY + (branchLength * sin(angleRad)).toFloat()

            val ctrlX = baseX + (branchLength * 0.5f * cos(angleRad)).toFloat()
            val ctrlY = trunkTopY - h * 0.12f

            val branch = Path().apply {
                moveTo(baseX, trunkTopY)
                quadraticBezierTo(ctrlX, ctrlY, endX, endY)
            }
            drawPath(branch, color = Gold, style = Stroke(width = w * 0.018f))

            // عقدة (ثمرة/ورقة) خضراء عند طرف كل غصن، بهالة متوهجة
            drawCircle(color = LeafAccent.copy(alpha = 0.25f), radius = w * 0.055f, center = Offset(endX, endY))
            drawCircle(color = LeafAccent, radius = w * 0.028f, center = Offset(endX, endY))
        }

        // عقدة ذهبية صغيرة أعلى الجذع (قمة السند)
        drawCircle(color = GoldLight.copy(alpha = 0.3f), radius = w * 0.05f, center = Offset(baseX, trunkTopY - h * 0.06f))
        drawCircle(color = GoldLight, radius = w * 0.025f, center = Offset(baseX, trunkTopY - h * 0.06f))
    }
}
