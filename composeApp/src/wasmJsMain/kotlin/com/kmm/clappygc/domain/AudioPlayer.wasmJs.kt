package com.kmm.clappygc.domain

import org.w3c.dom.Audio

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class AudioPlayer {

    private val audioElement = mutableMapOf<String, Audio>()


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

    private fun stopSound(filename: String) {
        audioElement[filename]?.let { audio ->
            audio.pause()
            audio.currentTime = 0.0
        }
    }

    private fun stopAllSounds() {
        audioElement.values.forEach { audio ->
            audio.pause()
            audio.currentTime = 0.0
        }
    }


    private fun playSound(filename: String, loop: Boolean = false) {
        val audio = audioElement[filename] ?: createAudioElement(filename).also {
            audioElement[filename] = it
        }
        audio.loop = loop
        audio.play().catch {
            println("Error playing audio file: $filename")
            it
        }

    }


    private fun createAudioElement(filename: String): Audio {
        val path = "src/commonMain/composeResources/files/$filename"
        return Audio(path).apply {
            onerror = { _, _, _, _, _ ->
                println("Error loading audio file: $path")
                null
            }
        }

    }
}