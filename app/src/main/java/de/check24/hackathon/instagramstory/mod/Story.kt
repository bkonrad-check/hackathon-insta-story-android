package de.check24.hackathon.instagramstory.mod

data class Story(
    val chapters: List<ChapterApi>,
    val id: Int,
    val preview: String,
    val title: String
)