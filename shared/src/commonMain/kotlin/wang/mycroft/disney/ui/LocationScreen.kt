package wang.mycroft.disney.ui

import androidx.compose.ui.graphics.drawscope.DrawScope


expect class Svg(svgBytes: ByteArray) {
    val width: Float
    val height: Float

    fun renderTo(scope: DrawScope)
}