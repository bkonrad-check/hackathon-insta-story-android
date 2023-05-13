package de.check24.hackathon.instagramstory.util

import android.graphics.Color.parseColor
import androidx.compose.ui.graphics.Color
import kotlin.time.Duration

fun String.colorFromHex() = Color(
    parseColor(
        when (this.startsWith("#", true)) {
            false -> "#$this"
            true -> this
        },
    ),
)

fun Duration.humanReadableFormat(): String {
    return this.toString()
        .split(" ")
        .first()
        .lowercase()
}