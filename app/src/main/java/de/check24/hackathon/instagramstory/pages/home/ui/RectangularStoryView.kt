package de.check24.hackathon.instagramstory.pages.home.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import de.check24.hackathon.instagramstory.mod.Story

/**
 * Rectangular story view
 *
 * @param shadowHeight
 * @param isLarge
 * @param height
 * @param modifier
 * @param subscription
 */
@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalCoilApi
@Composable
fun RectangularStoryView(
    shadowHeight: Float,
    isLarge: Boolean,
    modifier: Modifier,
    story: Story,
    onClick: (Story) -> Unit
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(5.dp),
        onClick = { onClick(story) }
    ) {
        Box(Modifier.fillMaxHeight()) {
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(data = story.preview)
                        .build()
                ),
                contentScale = ContentScale.Crop,
                contentDescription = "Content",
            )
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = shadowHeight
                        ),
                    )
            )
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(if (!isLarge) 5.dp else 10.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(
                    text = story.title,
                    maxLines = if (!isLarge) 2 else 4,
                    color = Color.White,
                    fontWeight = FontWeight.W800,
                    fontSize = if (!isLarge) 12.sp else 20.sp,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}
