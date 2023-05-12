package de.check24.hackathon.instagramstory.pages.story

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.check24.hackathon.instagramstory.pages.data.Chapter
import de.check24.hackathon.instagramstory.pages.data.Story
import de.check24.hackathon.instagramstory.pages.home.data.mockAPIResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.math.max
import kotlin.math.min

class StoryViewModel : ViewModel() {

    private val mutableChapters = MutableStateFlow<List<Chapter>>(listOf())
    val chapters: StateFlow<List<Chapter>> get() = mutableChapters

    private val mutableCurrentChapter = MutableStateFlow(0)
    val currentChapter: StateFlow<Int> get() = mutableCurrentChapter

    private lateinit var story: Story

    init {
        provideMedia()
    }


    private fun provideMedia() {
        story = mockAPIResponse().first()

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