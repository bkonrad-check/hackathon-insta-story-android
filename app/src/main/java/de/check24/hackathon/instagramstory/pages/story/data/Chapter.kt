package de.check24.hackathon.instagramstory.pages.story.data

data class Chapter (
    val visualType: StoryMediaType,
    val audioType: StoryMediaType,
    val media: StoryMedia,
    val startsFrom: Int?, // millis
    val endsAt: Int? // millis
)