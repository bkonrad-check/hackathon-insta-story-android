package de.check24.hackathon.instagramstory.pages.player

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PlayerViewModel : ViewModel() {
    private val mutableUrlToPlay: MutableStateFlow<String?> = MutableStateFlow(null)
    val urlToPlay: StateFlow<String?> = mutableUrlToPlay

    fun loadVideo() {
        viewModelScope.launch {
            mutableUrlToPlay.emit("https://dev.whost.ml/mixkit-pink-and-blue-ink-1192-medium.mp4")
        }
    }
}
