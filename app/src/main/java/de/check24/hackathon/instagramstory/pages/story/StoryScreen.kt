package de.check24.hackathon.instagramstory.pages.story

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun StoryScreen(viewModel: StoryViewModel = viewModel()) {
    Text(text = "Stories")
}