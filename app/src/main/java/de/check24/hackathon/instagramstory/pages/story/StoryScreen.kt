package de.check24.hackathon.instagramstory.pages.story

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import de.check24.hackathon.instagramstory.pages.story.ui.InstagramProgressIndicator
import de.check24.hackathon.instagramstory.pages.story.ui.StoryContent

@Composable
fun StoryScreen(viewModel: StoryViewModel = viewModel()) {
    InstagramStory(viewModel)
}

@Composable
fun InstagramStory(viewModel: StoryViewModel) {

    val chapters = viewModel.chapters.collectAsStateWithLifecycle().value
    val currentChapter = viewModel.currentChapter.collectAsStateWithLifecycle()
    val stepCount = chapters.size
    val isPaused = remember { mutableStateOf(false) }

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
                            awaitRelease()
                        } finally {
                            isPaused.value = false
                        }
                    }
                )
            }

        val chapter = chapters [currentChapter.value]

        StoryContent(imageModifier, chapter)

        InstagramProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            stepCount = stepCount,
            stepDuration = 2_000,
            unSelectedColor = Color.LightGray,
            selectedColor = Color.White,
            currentStep = currentChapter.value,
            onStepChanged = { viewModel.navigateToNext()},
            isPaused = isPaused.value,
            onComplete = { }
        )
    }
}


