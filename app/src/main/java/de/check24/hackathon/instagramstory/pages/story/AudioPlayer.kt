package de.check24.hackathon.instagramstory.pages.story

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import de.check24.hackathon.instagramstory.R

class AudioPlayer(
    context: Context
) : MediaPlayer(), MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {

    private val audioResourceId = R.raw.audio
    private val audioUri: Uri = Uri.parse("android.resource://${context.packageName}/$audioResourceId")

    init {
        setOnPreparedListener(this)
        setOnCompletionListener(this)
        setDataSource(context, audioUri)
        prepareAsync()
    }

    override fun onPrepared(mp: MediaPlayer?) {
        start()
    }

    override fun onCompletion(mp: MediaPlayer?) {
        stop()
        release()
    }
}

