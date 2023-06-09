package de.check24.hackathon.instagramstory.pages.story

import android.widget.Toast
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import de.check24.hackathon.instagramstory.mod.Cache
import de.check24.hackathon.instagramstory.mod.Story
import de.check24.hackathon.instagramstory.pages.story.ui.BannersDrawer
import de.check24.hackathon.instagramstory.pages.story.ui.InstagramProgressIndicator
import de.check24.hackathon.instagramstory.pages.story.ui.StoryContent
import de.check24.hackathon.instagramstory.util.humanReadableFormat
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

@Composable
fun StoryScreen(
    story: Story,
    viewModel: StoryViewModel = viewModel(
        factory = StoryViewModelFactory(
            story,
            LocalContext.current,
        ),
    ),
    onNavigateToStory: (Story) -> Unit,
    onNavigateBack: () -> Unit,
    onNavigateBackToHome: () -> Unit,
) {
    viewModel.onNavigateToStory = { onNavigateToStory(it) }
    viewModel.onNavigateBack = { onNavigateBack() }
    viewModel.onNavigateBackToHome = { onNavigateBackToHome() }
    InstagramStory(viewModel, onNavigateToStory, story, onNavigateBackToHome)
}

@Composable
fun InstagramStory(
    viewModel: StoryViewModel,
    onNavigateToStory: (Story) -> Unit,
    story: Story,
    onBackPressed: () -> Unit,
) {
    val chapters = viewModel.chapters.collectAsStateWithLifecycle().value
    val stepCount = chapters.size
    val snackbar = viewModel.snackbar.collectAsStateWithLifecycle()
    val currentChapterIndex = viewModel.currentChapterIndex.collectAsStateWithLifecycle()
    val isPaused = viewModel.isPaused.collectAsStateWithLifecycle().value
    val seekFlag = viewModel.seekFlag.collectAsStateWithLifecycle().value
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
                    },
                )
            }

        val chapter = remember("$currentChapterIndex") {
            chapters[currentChapterIndex.value]
        }
        StoryContent(imageModifier, chapter, isPaused, seekFlag)
        BannersDrawer(
            chapter.banners,
            viewModel::onInteractionClick,
            viewModel::onPress,
            viewModel::onPressRelease,
        )

        InstagramProgressIndicator(
            stepCount,
            chapter.length,
            currentChapterIndex.value,
            isPaused,
            viewModel,
            story,
            onBackPressed,
            onNavigateToStory,
        )

        if (snackbar.value != null) {
            Toast.makeText(LocalContext.current, snackbar.value, Toast.LENGTH_SHORT).show()
        }
    }
}

@Composable
fun StoryTitle(
    story: Story,
    currentChapterIndex: Int,
) {
    Row {
        Text(
            text = story.title,
            modifier = Modifier.padding(start = 8.dp, top = 4.dp),
            color = Color.White,
            textAlign = TextAlign.Left,
            fontSize = 12.sp,
        )
    }
    Row {
        val timeDelta = System.currentTimeMillis() / 1000L - story.chapters[currentChapterIndex].posted
        Text(
            text = "Posted ${timeDelta.seconds.humanReadableFormat()}",
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 1.dp),
            color = Color.White,
            textAlign = TextAlign.Left,
            fontSize = 10.sp,
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
    onNavigateToStory: (Story) -> Unit,
) {
    Column {
        InstagramProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            stepCount = stepCount,
            stepDuration = chapterLength,
            unSelectedColor = Color.LightGray,
            selectedColor = Color.White,
            currentStep = currentChapterIndex,
            onStepChanged = { viewModel.navigateToNext(isAutomatic = true) },
            isPaused = isPaused,
            onComplete = {
                val stories = Cache.stories
                if (stories.lastOrNull() == story) {
                    onBackPressed()
                } else {
                    val nextStory = stories.indexOf(story) + 1
                    onNavigateToStory(stories[nextStory])
                }
            },
        )
        StoryTitle(story, currentChapterIndex)
    }
}
