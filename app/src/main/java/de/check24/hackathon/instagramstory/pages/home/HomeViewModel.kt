package de.check24.hackathon.instagramstory.pages.home

import androidx.lifecycle.ViewModel
import de.check24.hackathon.instagramstory.pages.data.Story
import de.check24.hackathon.instagramstory.pages.home.data.mockDataStories
import de.check24.hackathon.instagramstory.pages.story.data.StoryMedia
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel: ViewModel() {
    private val mutableStories = MutableStateFlow<List<Story>>(listOf())
    val stories: StateFlow<List<Story>> get() = mutableStories

    init {
        mutableStories.value = mockDataStories()
    }
}