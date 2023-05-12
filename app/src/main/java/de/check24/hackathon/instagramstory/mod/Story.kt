package de.check24.hackathon.instagramstory.mod

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Story(
    val chapters: List<ChapterApi>,
    val id: Int,
    val preview: String,
    val title: String
) : Parcelable