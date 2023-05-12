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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import de.check24.hackathon.instagramstory.R
import de.check24.hackathon.instagramstory.pages.story.ui.InstagramProgressIndicator
import de.check24.hackathon.instagramstory.pages.story.ui.StoryContent
import kotlin.math.max
import kotlin.math.min

@Composable
fun StoryScreen(viewModel: StoryViewModel = viewModel()) {
    InstagramStory()
}

@Composable
fun InstagramStory() {
    val images = remember {
        listOf(
            R.drawable.image_1,
            R.drawable.image_2,
            R.drawable.image_3,
            R.drawable.image_4,
            R.drawable.image_5,
            R.drawable.image_6
        )
    }

    val stepCount = images.size
    val currentStep = remember { mutableStateOf(0) }
    val isPaused = remember { mutableStateOf(false) }

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val imageModifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { offset ->
                        currentStep.value = if (offset.x < constraints.maxWidth / 2) {
                            max(0, currentStep.value - 1)
                        } else {
                            min(stepCount - 1, currentStep.value + 1)
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

        StoryContent(imageModifier, currentStep, images)

        InstagramProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            stepCount = stepCount,
            stepDuration = 2_000,
            unSelectedColor = Color.LightGray,
            selectedColor = Color.White,
            currentStep = currentStep.value,
            onStepChanged = { currentStep.value = it },
            isPaused = isPaused.value,
            onComplete = { }
        )
    }
}


