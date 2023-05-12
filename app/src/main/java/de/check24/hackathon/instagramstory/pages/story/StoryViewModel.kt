package de.check24.hackathon.instagramstory.pages.story

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.check24.hackathon.instagramstory.mod.ChapterApi
import de.check24.hackathon.instagramstory.mod.Story
import de.check24.hackathon.instagramstory.network.DataSource
import de.check24.hackathon.instagramstory.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.math.max
import kotlin.math.min

class StoryViewModel : ViewModel() {

    private val mutableChapters = MutableStateFlow<List<ChapterApi>>(listOf())
    val chapters: StateFlow<List<ChapterApi>> get() = mutableChapters

    private val mutableCurrentChapter = MutableStateFlow(0)
    val currentChapter: StateFlow<Int> get() = mutableCurrentChapter

    private lateinit var story: Story

    init {
        data()
    }

    fun data() {
        viewModelScope.launch {
            story = DataSource(RetrofitClient().createService()).stories().stories.get(3)
            mutableChapters.emit(story.chapters)
        }
    }

    fun navigateToNext() {
        viewModelScope.launch {
            mutableCurrentChapter.emit(min(story.chapters.size - 1, currentChapter.value + 1))
        }
    }

    fun navigateToPrevious() {
        viewModelScope.launch {
            mutableCurrentChapter.emit(max(0, currentChapter.value - 1))
        }
    }

}