package de.check24.hackathon.instagramstory.pages.story

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.check24.hackathon.instagramstory.mod.ChapterApi
import de.check24.hackathon.instagramstory.mod.Story
import kotlin.math.max
import kotlin.math.min
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StoryViewModel(context: Context, private val story: Story) : ViewModel() {

    private val mutableChapters = MutableStateFlow<List<ChapterApi>>(listOf())
    val chapters: StateFlow<List<ChapterApi>> get() = mutableChapters

    private val mutableCurrentChapter = MutableStateFlow(0)
    val currentChapter: StateFlow<Int> get() = mutableCurrentChapter

    init {
        mutableChapters.value = story.chapters
    }

    private val player = AudioPlayer(context)

    private val _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> = _isPlaying

    val audioPlayer = createAudioPlayer()


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

    fun togglePlayPause() {
        _isPlaying.value = !_isPlaying.value
    }

    private fun createAudioPlayer(): AudioPlayer {
        // Initialize audio player and set up callbacks for playback events

        player.setOnPreparedListener {
            player.start()
        }

        player.setOnCompletionListener {
            _isPlaying.value = false
        }

        return player
    }

    override fun onCleared() {
        super.onCleared()
        audioPlayer.release() // Release resources when ViewModel is destroyed
    }

}