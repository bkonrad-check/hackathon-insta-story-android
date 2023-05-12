package de.check24.hackathon.instagramstory.pages.player

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import de.check24.hackathon.instagramstory.ui.theme.Background

@Composable
@UnstableApi
fun PlayerScreen(viewModel: PlayerViewModel = viewModel()) {
    val context = LocalContext.current

    val player = remember {
        ExoPlayer.Builder(context).build().apply {
            playWhenReady = true
        }
    }

    viewModel.loadVideo()
    viewModel.urlToPlay.collectAsStateWithLifecycle().value?.let { url ->

        val mediaItem = MediaItem.fromUri(url)
        player.setMediaItem(mediaItem)
        // Prepare the player.
        player.prepare()
        // Adds view to Compose

        val playerView = remember {
            PlayerView(context, null, 0).apply {
                this.player = player
                this.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL
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
