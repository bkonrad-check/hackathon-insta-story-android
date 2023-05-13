package de.check24.hackathon.instagramstory.pages.story

import android.content.Context
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AudioViewModel(
    context: Context
) : ViewModel() {

    private val player = AudioPlayer(context)

    private val _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> = _isPlaying

    val audioPlayer = createAudioPlayer()

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

