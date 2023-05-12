package de.check24.hackathon.instagramstory.mod

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChapterApi(

    val endAt: Int?,
    val id: Int,
    val length: Int,
    val posted: Int,
    val startAt: Int?,
    val status: String,
    val type: String,
    val url: String
): Parcelable {
    fun isContinuousPlayback(): Boolean {
        if (startAt != null && endAt != null) {
            return startAt > 0
        }
        return false
    }
}