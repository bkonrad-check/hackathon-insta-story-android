package de.check24.hackathon.instagramstory.pages.story

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.check24.hackathon.instagramstory.R
import de.check24.hackathon.instagramstory.pages.story.data.StoryImage
import de.check24.hackathon.instagramstory.pages.story.data.StoryMedia
import de.check24.hackathon.instagramstory.pages.story.data.StoryVideo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StoryViewModel : ViewModel() {

    private val mutableMediaData = MutableStateFlow<List<StoryMedia>>(listOf())
    val mediaData: StateFlow<List<StoryMedia>> get() = mutableMediaData

    private val mutableCurrentChapter = MutableStateFlow(0)
    val currentChapter: StateFlow<Int> get() = mutableCurrentChapter



    init {
        provideMedia()
    }



    private fun provideMedia() {
        mutableMediaData.value =
            listOf(
                StoryImage(R.drawable.image_1),
                StoryVideo(R.raw.example),
                StoryImage(R.drawable.image_2)
            )

    }

    fun navigateToNext() {
        viewModelScope.launch {
            mutableCurrentChapter.emit(currentChapter.value + 1)
        }
    }

    fun navigateToPrevious() {
        viewModelScope.launch {
            mutableCurrentChapter.emit(currentChapter.value + 1)
        }
    }

}