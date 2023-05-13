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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import de.check24.hackathon.instagramstory.mod.Cache
import de.check24.hackathon.instagramstory.mod.Story
import de.check24.hackathon.instagramstory.pages.story.ui.InstagramProgressIndicator
import de.check24.hackathon.instagramstory.pages.story.ui.StoryContent

@Composable
fun StoryScreen(
    story: Story,
    viewModel: StoryViewModel = viewModel(
        factory = StoryViewModelFactory(
            story,
            LocalContext.current
        )
    ),
    onNavigateToStory: (Story) -> Unit,
    onBackPressed: () -> Unit
) {
    viewModel.onNavigateToStory = { onNavigateToStory(it) }
    viewModel.onNavigateBack = { onBackPressed() }
    InstagramStory(viewModel, onNavigateToStory, story, onBackPressed)
}

@Composable
fun InstagramStory(
    viewModel: StoryViewModel,
    onNavigateToStory: (Story) -> Unit,
    story: Story,
    onBackPressed: () -> Unit
) {
    val chapters = viewModel.chapters.collectAsStateWithLifecycle().value
    val stepCount = chapters.size
    val currentChapterIndex = viewModel.currentChapterIndex.collectAsStateWithLifecycle()
    val isPaused = viewModel.isPaused.collectAsStateWithLifecycle().value
    val isPlaying by viewModel.isPlaying.collectAsState()
    if (!isPlaying) viewModel.audioPlayer.start()

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
            chapters[currentChapterIndex.value]
        }
        StoryContent(imageModifier, chapter, isPaused)

        InstagramProgressIndicator(
            stepCount,
            chapter.length,
            currentChapterIndex.value,
            isPaused,
            viewModel,
            story,
            onBackPressed,
            onNavigateToStory
        )
    }
}

@Composable
fun InstagramProgressIndicator(
    stepCount: Int,
    chapterLength: Int,
    currentChapterIndex: Int,
    isPaused: Boolean,
    viewModel: StoryViewModel,
    story: Story,
    onBackPressed: () -> Unit,
    onNavigateToStory: (Story) -> Unit
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
        onComplete = {
            val stories = Cache.stories
            if (stories.lastOrNull() == story) {
                onBackPressed()
            } else {
                val nextStory = stories.indexOf(story) + 1
                onNavigateToStory(stories[nextStory])
            }
        }
    )
}


