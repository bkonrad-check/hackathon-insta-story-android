@file:UnstableApi package de.check24.hackathon.instagramstory.pages.story.ui


import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DataSource
import androidx.media3.datasource.DataSpec
import androidx.media3.datasource.RawResourceDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.LoopingMediaSource
import androidx.media3.exoplayer.source.MediaSource
import androidx.media3.extractor.ogg.OggExtractor
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import de.check24.hackathon.instagramstory.R
import de.check24.hackathon.instagramstory.pages.story.data.StoryImage
import de.check24.hackathon.instagramstory.pages.story.data.StoryMedia
import de.check24.hackathon.instagramstory.ui.theme.Background


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
            val context = LocalContext.current

            val player = remember {
                ExoPlayer.Builder(context).build().apply {
                    playWhenReady = true
                }
            }
            val rawVideoUri = Uri.parse("android.resource://${context.packageName}/${R.raw.example}")

            val mediaItem = MediaItem.fromUri(rawVideoUri)
            player.setMediaItem(mediaItem)
            // Prepare the player.
            player.prepare()
            // Adds view to Compose

            val playerView = remember {
                PlayerView(context, null, 0).apply {
                    this.player = player
                    this.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
                    this.useController = false
                }
            }

            Surface(color = Background) {
                AndroidView(
                    modifier = Modifier.fillMaxSize(),
                    factory = {
                        playerView
                    },
                )
            }
        }
    }
}