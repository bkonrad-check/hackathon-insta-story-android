package de.check24.hackathon.instagramstory.util

import android.graphics.Color.parseColor
import androidx.compose.ui.graphics.Color

fun String.colorFromHex() = Color(
    parseColor(
        when (this.startsWith("#", true)) {
            false -> "#$this"
            true -> this
        },
    ),
)
