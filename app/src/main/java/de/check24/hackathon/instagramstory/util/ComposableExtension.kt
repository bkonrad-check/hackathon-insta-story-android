package de.check24.hackathon.instagramstory.util

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Composable
fun Int.pxToSp(): TextUnit = with(LocalDensity.current) {
    this@pxToSp.toSp()
}

@Composable
fun Int.pxToDp(): Dp = with(LocalDensity.current) {
    this@pxToDp.toDp()
}

@Composable
fun Modifier.advancedShadow(
    color: Color = Color.Black,
    alpha: Float = 1f,
    cornersRadius: Dp = 0.dp,
    shadowBlurRadius: Dp = 0.dp,
    offsetY: Dp = 0.dp,
    offsetX: Dp = 0.dp,
) = drawBehind {
    val shadowColor = color.copy(alpha = alpha).toArgb()
    val transparentColor = color.copy(alpha = 0f).toArgb()

    drawIntoCanvas {
        val paint = Paint()
        val frameworkPaint = paint.asFrameworkPaint()
        frameworkPaint.color = transparentColor
        frameworkPaint.setShadowLayer(
            shadowBlurRadius.toPx(),
            offsetX.toPx(),
            offsetY.toPx(),
            shadowColor,
        )
        it.drawRoundRect(
            0f,
            0f,
            this.size.width,
            this.size.height,
            cornersRadius.toPx(),
            cornersRadius.toPx(),
            paint,
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AnimatedBox(
    clickableCallback: (() -> Unit)?,
    longPress: (() -> Unit)?,
    longPressRelease: (() -> Unit)?,
    modifier: Modifier,
    content: @Composable BoxScope.() -> Unit,
) {
    val selected = remember { mutableStateOf(false) }
    val scale = animateFloatAsState(if (selected.value) 0.9f else 1f)
    var modifierLocal: Modifier = modifier
    if (clickableCallback != null) {
        modifierLocal = modifierLocal
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { offset ->
                        selected.value = true
                        clickableCallback()
                        selected.value = false
                    },
                    onPress = {
                        try {
                            selected.value = true
                            awaitRelease()
                        } finally {
                            selected.value = false
                            longPressRelease?.invoke()
                        }
                    },
                    onLongPress = {
                        longPress?.invoke()
                        selected.value = true
                    },
                )
            }
    }
    Box(
        modifier = modifierLocal.scale(scale.value),
    ) {
        content()
    }
}
