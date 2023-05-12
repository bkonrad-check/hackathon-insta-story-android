package de.check24.hackathon.instagramstory.pages.story.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

@Composable
fun StoryContent(
    modifier: Modifier,
    currentStep: MutableState<Int>,
    images: List<Int>
) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = images[currentStep.value]),
            contentDescription = "StoryImage",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.fillMaxSize()
        )
    }
}