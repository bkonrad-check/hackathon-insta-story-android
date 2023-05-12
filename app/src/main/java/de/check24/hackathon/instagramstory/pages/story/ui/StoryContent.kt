package de.check24.hackathon.instagramstory.pages.story.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import de.check24.hackathon.instagramstory.pages.story.data.StoryImage
import de.check24.hackathon.instagramstory.pages.story.data.StoryMedia

@Composable
fun StoryContent(
    modifier: Modifier,
    currentStep: StoryMedia
) {
    Box(modifier = modifier) {
        if(currentStep is StoryImage) {
            Image(
                painter = painterResource(id = currentStep.path),
                contentDescription = "StoryImage",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        } else {

        }
    }
}