package de.check24.hackathon.instagramstory.pages.story

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import de.check24.hackathon.instagramstory.mod.ChapterApi
import de.check24.hackathon.instagramstory.mod.Story
import de.check24.hackathon.instagramstory.pages.story.ui.InstagramProgressIndicator
import de.check24.hackathon.instagramstory.pages.story.ui.StoryContent

@Composable
fun StoryScreen(
    story: Story,
    viewModel: StoryViewModel = viewModel(factory = StoryViewModelFactory(story))
) {
    InstagramStory(viewModel)
}

@Composable
fun InstagramStory(viewModel: StoryViewModel) {
    val chapters = viewModel.chapters.collectAsStateWithLifecycle()
    val currentChapterIndex = viewModel.currentChapterIndex.collectAsStateWithLifecycle()
    val stepCount = chapters.value.size
    val isPaused = viewModel.isPaused.collectAsStateWithLifecycle()

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
                            awaitRelease()
                        } finally {
                            viewModel.onPressRelease()
                        }
                    },
                    onLongPress = {
                        viewModel.onPress()
                    }
                )
            }

        val chapter = remember("$currentChapterIndex") {
            chapters.value[currentChapterIndex.value]
        }
        StoryContent(imageModifier, chapter, viewModel)

        InstagramProgressIndicator(
            stepCount,
            chapter.length,
            currentChapterIndex.value,
            isPaused.value,
            viewModel
        )
    }
}

@Composable
fun InstagramProgressIndicator(
    stepCount: Int,
    chapterLength: Int,
    currentChapterIndex: Int,
    isPaused: Boolean,
    viewModel: StoryViewModel
) {
    InstagramProgressIndicator(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        stepCount = stepCount,
        stepDuration = chapterLength,
        unSelectedColor = Color.LightGray,
        selectedColor = Color.White,
        currentStep = currentChapterIndex,
        onStepChanged = { viewModel.navigateToNext() },
        isPaused = isPaused,
        onComplete = { }
    )
}


