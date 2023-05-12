package de.check24.hackathon.instagramstory.mod

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class ChapterApi(

    val endAt: Int,
    val id: Int,
    val length: Int,
    val posted: Int,
    val startAt: Int,
    val status: String,
    val type: String,
    val url: String
) :Parcelable