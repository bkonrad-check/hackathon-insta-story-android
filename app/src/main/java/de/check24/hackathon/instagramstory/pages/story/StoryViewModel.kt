package de.check24.hackathon.instagramstory.pages.story

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.check24.hackathon.instagramstory.mod.ChapterApi
import de.check24.hackathon.instagramstory.mod.Story
import kotlin.math.max
import kotlin.math.min
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StoryViewModel(private val story: Story) : ViewModel() {

    private val mutableChapters = MutableStateFlow<List<ChapterApi>>(listOf())
    val chapters: StateFlow<List<ChapterApi>> get() = mutableChapters

    private val mutableCurrentChapterIndex = MutableStateFlow(0)
    val currentChapterIndex: StateFlow<Int> get() = mutableCurrentChapterIndex

    private val mutableIsPaused = MutableStateFlow(false)
    val isPaused: StateFlow<Boolean> get() = mutableIsPaused

    init {
        mutableChapters.value = story.chapters
    }

    fun onPress() {
        viewModelScope.launch {
            mutableIsPaused.emit(true)
            Log.d("####", "onPress")
        }
    }

    fun onPressRelease() {
        if (isPaused.value) {
            viewModelScope.launch {
                mutableIsPaused.emit(false)
                Log.d("####", "onPressRelease")
            }
        }
    }

    fun navigateToNext() {
        viewModelScope.launch {
            val newIndex = min(story.chapters.size - 1, currentChapterIndex.value + 1)
            if (newIndex != mutableCurrentChapterIndex.value) {
                Log.d("####", "navigateToNext")
                mutableCurrentChapterIndex.emit(newIndex)
            }
        }
    }

    fun navigateToPrevious() {
        viewModelScope.launch {
            val newIndex = max(0, currentChapterIndex.value - 1)
            if (newIndex != mutableCurrentChapterIndex.value) {
                Log.d("####", "navigateToPrevious")
                mutableCurrentChapterIndex.emit(newIndex)
            }
        }
    }

}