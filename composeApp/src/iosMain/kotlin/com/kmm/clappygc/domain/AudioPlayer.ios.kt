package com.kmm.clappygc.domain

import platform.AVFAudio.AVAudioPlayer
import platform.AVFAudio.AVAudioSession
import platform.AVFAudio.AVAudioSessionCategoryPlayback
import platform.AVFAudio.setActive
import platform.Foundation.NSBundle
import platform.Foundation.NSURL
import platform.Foundation.NSURL.Companion.fileURLWithPath

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
actual class AudioPlayer {

    private var audioPlayers: MutableMap<String, AVAudioPlayer?> = mutableMapOf()

    private var fallingSoundPlayer: AVAudioPlayer? = null

    init {

        val session = AVAudioSession.sharedInstance()
        session.setCategory(AVAudioSessionCategoryPlayback, error = null)
        session.setActive(true, null)
    }

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

    private fun playSound(soundName: String): AVAudioPlayer? {
        val url = getSoundURL(soundName)
        val player = url?.let { AVAudioPlayer(it, null) }
        player?.prepareToPlay()
        player?.play()

        audioPlayers[soundName] = player
        return player
    }

    private fun getSoundURL(resourceName: String): NSURL? {
        val bundle = NSBundle.mainBundle()
        val path = bundle.pathForResource(resourceName, "wav")
        return path?.let {
            fileURLWithPath(it)
        }
    }

}