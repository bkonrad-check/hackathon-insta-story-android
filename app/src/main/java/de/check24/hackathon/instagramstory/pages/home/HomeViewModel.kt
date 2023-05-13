package de.check24.hackathon.instagramstory.pages.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.check24.hackathon.instagramstory.mod.Cache
import de.check24.hackathon.instagramstory.mod.Story
import de.check24.hackathon.instagramstory.network.DataSource
import de.check24.hackathon.instagramstory.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val mutableStories = MutableStateFlow<List<Story>>(listOf())
    val stories: StateFlow<List<Story>> get() = mutableStories

    init {
        viewModelScope.launch {
            val stories = DataSource(RetrofitClient().createService()).stories().stories
            mutableStories.value = stories
            Cache.stories = stories
        }
    }
}