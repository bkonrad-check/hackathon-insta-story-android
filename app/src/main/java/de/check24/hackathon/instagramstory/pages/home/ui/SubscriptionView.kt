package de.check24.hackathon.instagramstory.pages.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import de.check24.hackathon.instagramstory.pages.data.Story
import de.check24.hackathon.instagramstory.pages.home.data.mockDataStories

@ExperimentalCoilApi
@Composable
fun StoryView(onNavigateToStory: (Story) -> Unit, stories: List<Story>) {
    Column(
        modifier = Modifier.padding(top = 20.dp)
    ) {
        Text(
            text = "Stories",
            color = Color.Black,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 10.dp, start = 5.dp, end = 5.dp)
        )
        LazyRow {
            items(stories.size) { index ->
                RectangularStoryView(
                    shadowHeight = 100f,
                    story = stories[index],
                    isLarge = false,
                    onClick= onNavigateToStory,
                    modifier = Modifier
                        .height(160.dp)
                        .width(110.dp)
                        .padding(end = 5.dp, start = 5.dp)
                        .background(color = Color.DarkGray, RoundedCornerShape(5.dp))
                )
            }
        }
    }
}