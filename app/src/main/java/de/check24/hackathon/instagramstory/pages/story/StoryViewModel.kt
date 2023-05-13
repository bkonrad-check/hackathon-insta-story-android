package de.check24.hackathon.instagramstory.pages.story

import android.util.Log
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.check24.hackathon.instagramstory.mod.Cache
import de.check24.hackathon.instagramstory.mod.ChapterApi
import de.check24.hackathon.instagramstory.mod.Story
import kotlin.math.max
import kotlin.math.min
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StoryViewModel(context: Context, private val story: Story) : ViewModel() {
    var onNavigateToStory: ((Story) -> Unit)?= null
    var onNavigateBack: (() -> Unit)?= null
    var onNavigateBackToHome: (() -> Unit)?= null
    private val mutableChapters = MutableStateFlow<List<ChapterApi>>(listOf())
    val chapters: StateFlow<List<ChapterApi>> get() = mutableChapters

    private val mutableCurrentChapterIndex = MutableStateFlow(0)
    val currentChapterIndex: StateFlow<Int> get() = mutableCurrentChapterIndex

    private val mutableIsPaused = MutableStateFlow(false)
    val isPaused: StateFlow<Boolean> get() = mutableIsPaused

    private val mutableSnackbar: MutableStateFlow<String?> = MutableStateFlow(null)
    val snackbar: StateFlow<String?> get() = mutableSnackbar

    init {
        mutableChapters.value = story.chapters
    }

    private val player = AudioPlayer(context)
    private val _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> = _isPlaying

    val audioPlayer = createAudioPlayer()


    fun onPress() {
        viewModelScope.launch {
            mutableIsPaused.emit(true)
            _isPlaying.value = false
            Log.d("####", "onPress")
        }
    }

    fun onPressRelease() {
        if (isPaused.value) {
            viewModelScope.launch {
                mutableIsPaused.emit(false)
                _isPlaying.value = true
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
            else if (newIndex == mutableCurrentChapterIndex.value) {
                val stories = Cache.stories
                if (story == stories.lastOrNull()) {
                    onNavigateBackToHome?.invoke()
                } else {
                    val nextStory = stories.indexOf(story) +1
                    onNavigateToStory?.invoke(Cache.stories[nextStory])
                }
            }
        }
    }

    fun navigateToPrevious() {
        viewModelScope.launch {
            val newIndex = max(0, currentChapterIndex.value - 1)
            if (newIndex != mutableCurrentChapterIndex.value) {
                Log.d("####", "navigateToPrevious")
                mutableCurrentChapterIndex.emit(newIndex)
            } else {
                onNavigateBack?.invoke()
            }
        }
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

    fun onInteractionClick(deepLink: String) {
        viewModelScope.launch {
            mutableSnackbar.emit("Clicked $deepLink")
        }
    }
}
