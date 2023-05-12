package de.check24.hackathon.instagramstory.pages.story

import androidx.lifecycle.ViewModel
import de.check24.hackathon.instagramstory.R
import de.check24.hackathon.instagramstory.pages.story.data.StoryImage
import de.check24.hackathon.instagramstory.pages.story.data.StoryMedia
import de.check24.hackathon.instagramstory.pages.story.data.StoryVideo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class StoryViewModel : ViewModel() {

    private val mutableMediaData = MutableStateFlow<List<StoryMedia>>(listOf())
    val mediaData: StateFlow<List<StoryMedia>> get() = mutableMediaData

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

}