package de.check24.hackathon.instagramstory.mod

data class ChapterApi(
    val banners: List<Any>,
    val endAt: Int?,
    val id: Int,
    val length: Int,
    val posted: Int,
    val startAt: Int?,
    val status: String,
    val type: String,
    val url: String
)