@file:UnstableApi

package de.check24.hackathon.instagramstory.pages.story.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import de.check24.hackathon.instagramstory.mod.ChapterApi
import de.check24.hackathon.instagramstory.ui.theme.Background

@Composable
fun StoryContent(
    modifier: Modifier,
    chapter: ChapterApi,
    isPaused: Boolean,
    seekFlag: Int
) {

    Box(modifier = modifier) {
        if (chapter.type == "IMAGE") {
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(data = chapter.url)
                        .build()
                ),
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

            val mediaItem = remember { mutableStateOf(MediaItem.fromUri(chapter.url)) }

            // Prepare the player and load new media item when url changes.

            LaunchedEffect(chapter.url) {
                player.stop()
                player.clearMediaItems()
                mediaItem.value = MediaItem.fromUri(chapter.url)
                player.setMediaItem(mediaItem.value)
                player.prepare()
            }

            // Seek to the specified start time only if necessary.
            LaunchedEffect(seekFlag) {
                if (chapter.startAt != null && chapter.endAt != null) {
                    player.seekTo(chapter.startAt.toLong())
                }
            }

            val playerView = remember {
                PlayerView(context, null, 0).apply {
                    this.player = player
                    this.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
                    this.useController = false
                }
            }
            VideoSurface(playerView)

            if (isPaused) {
                player.pause()
            } else {
                player.play()
            }
        }
    }
}


@Composable
fun VideoSurface(playerView: PlayerView) {
    Surface(color = Background) {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = {
                playerView
            },
        )
    }
}