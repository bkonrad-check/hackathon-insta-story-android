package de.check24.hackathon.instagramstory.pages.story

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import de.check24.hackathon.instagramstory.mod.Story
import de.check24.hackathon.instagramstory.pages.story.ui.InstagramProgressIndicator
import de.check24.hackathon.instagramstory.pages.story.ui.StoryContent

@Composable
fun StoryScreen(
    story: Story,
    viewModel: StoryViewModel = viewModel(factory = StoryViewModelFactory(story))
) {
    val context = LocalContext.current
    val audioViewModel = viewModel(key = "audioViewModel") { AudioViewModel(context) }
    InstagramStory(viewModel, audioViewModel, story)
}

@Composable
fun InstagramStory(viewModel: StoryViewModel, audioViewModel: AudioViewModel, story: Story) {
    val chapters = story.chapters
    val currentChapter = viewModel.currentChapter.collectAsStateWithLifecycle()
    val stepCount = chapters.size
    val isPaused = remember { mutableStateOf(false) }
    val isPlaying by audioViewModel.isPlaying.collectAsState()
    if (!isPlaying) audioViewModel.audioPlayer.start()

    if (stepCount == 0) return

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val imageModifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { offset ->
                        if (offset.x < constraints.maxWidth / 2) {
                            viewModel.navigateToPrevious()
                        } else {
                            viewModel.navigateToNext()
                        }
                    },
                    onPress = {
                        try {
                            isPaused.value = true
                            audioViewModel.togglePlayPause()
                            awaitRelease()
                        } finally {
                            audioViewModel.togglePlayPause()
                            isPaused.value = false
                        }
                    }
                )
            }

        val chapter = chapters[currentChapter.value]

        StoryContent(imageModifier, chapter)

        InstagramProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            stepCount = stepCount,
            stepDuration = chapter.length,
            unSelectedColor = Color.LightGray,
            selectedColor = Color.White,
            currentStep = currentChapter.value,
            onStepChanged = { viewModel.navigateToNext() },
            isPaused = isPaused.value,
            onComplete = { }
        )
    }
}


