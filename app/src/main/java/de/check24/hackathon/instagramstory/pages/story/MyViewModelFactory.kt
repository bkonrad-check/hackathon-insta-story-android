package de.check24.hackathon.instagramstory.pages.story

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import de.check24.hackathon.instagramstory.mod.Story

class StoryViewModelFactory(private val story: Story) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T= StoryViewModel(story) as T
}