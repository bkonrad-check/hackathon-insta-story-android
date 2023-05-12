package de.check24.hackathon.instagramstory.pages.story

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.check24.hackathon.instagramstory.R
import de.check24.hackathon.instagramstory.pages.story.data.Chapter
import de.check24.hackathon.instagramstory.pages.story.data.Story
import de.check24.hackathon.instagramstory.pages.story.data.StoryImage
import de.check24.hackathon.instagramstory.pages.story.data.StoryMedia
import de.check24.hackathon.instagramstory.pages.story.data.StoryMediaType
import de.check24.hackathon.instagramstory.pages.story.data.StoryVideo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.math.max
import kotlin.math.min

class StoryViewModel : ViewModel() {

    private val mutableMediaData = MutableStateFlow<List<StoryMedia>>(listOf())
    val mediaData: StateFlow<List<StoryMedia>> get() = mutableMediaData

    private val mutableChapters = MutableStateFlow<List<Chapter>>(listOf())
    val chapters: StateFlow<List<Chapter>> get() = mutableChapters

    private val mutableCurrentChapter = MutableStateFlow(0)
    val currentChapter: StateFlow<Int> get() = mutableCurrentChapter

    private lateinit var story: Story

    init {
        provideMedia()
    }


    private fun provideMedia() {
        story = Story(
            "First Story",
            "This is a fancy story, it explains you in 24 easy steps how to do something",
            listOf(
                Chapter(
                    StoryMediaType.Standalone,
                    StoryMediaType.Standalone,
                    StoryImage(R.drawable.image_1),
                    null,
                    null
                ),
                Chapter(
                    StoryMediaType.Standalone,
                    StoryMediaType.Standalone,
                    StoryVideo(R.raw.example),
                    null,
                    null
                ),
                Chapter(
                    StoryMediaType.Standalone,
                    StoryMediaType.Standalone,
                    StoryImage(R.drawable.image_2),
                    null,
                    null
                )
            )
        )
        viewModelScope.launch {
            mutableChapters.emit(story.chapters)
        }
    }

    fun navigateToNext() {
        viewModelScope.launch {
            mutableCurrentChapter.emit(min(2, currentChapter.value + 1))
        }
    }

    fun navigateToPrevious() {
        viewModelScope.launch {
            mutableCurrentChapter.emit(max(0, currentChapter.value - 1))
        }
    }

}