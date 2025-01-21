package com.kmm.clappygc.domain

import android.content.Context
import android.media.SoundPool
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import clappygamelearning.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class AudioPlayer(
    context: Context
) {

    private val loopingPlayer = ExoPlayer.Builder(context).build()

    private val mediaItems = soundResList.map {
        MediaItem.fromUri(Res.getUri(it))
    }

    private val soundPool = SoundPool.Builder()
        .setMaxStreams(3).build()


    actual fun playGameOverSound() {
    }

    actual fun playJumpSound() {
    }

    actual fun playFallingSound() {
    }

    actual fun stopFallingSound() {
    }

    actual fun playGameSoundInLoop() {
    }

    actual fun stopGameSound() {
    }

    actual fun release() {
    }
}