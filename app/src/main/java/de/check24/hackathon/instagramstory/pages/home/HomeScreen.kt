package de.check24.hackathon.instagramstory.pages.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import de.check24.hackathon.instagramstory.pages.data.Story
import de.check24.hackathon.instagramstory.pages.home.ui.StoryView

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    onNavigateToStory: (Story) -> Unit
) {
    val state = viewModel.stories.collectAsStateWithLifecycle()
    Column {
        Column {
            StoryView(onNavigateToStory, stories = state.value)
        }
    }

}
