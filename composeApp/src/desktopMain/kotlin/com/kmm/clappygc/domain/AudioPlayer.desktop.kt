package com.kmm.clappygc.domain

import java.io.FileInputStream
import java.io.FileNotFoundException
import java.nio.file.Files
import java.nio.file.Paths
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.DataLine
import javax.sound.sampled.SourceDataLine
import kotlin.concurrent.thread

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class AudioPlayer {

    private val audioCache = mutableMapOf<String, ByteArray>()
    private val playingLines = mutableMapOf<String, SourceDataLine>()


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

    private  fun stopSound(fileName: String){
        synchronized(playingLines){
            playingLines[fileName]?.let { line ->
                line.stop()
                line.close()
                playingLines.remove(fileName)
            }
        }
    }

    private fun playSound(fileName: String, loop : Boolean = false){
        thread {
            try {
                val audioData = audioCache[fileName] ?: loadAudioFile(fileName).also {
                    audioCache[fileName] = it
                }

                val inputStream = AudioSystem.getAudioInputStream(audioData.inputStream())
                val format = inputStream.format
                val info = DataLine.Info(SourceDataLine::class.java, format)
                val line = AudioSystem.getLine(info) as SourceDataLine

                line.open(format)
                line.start()

                synchronized(playingLines){
                    playingLines[fileName] = line
                }

                val buffer = ByteArray(4096)
                var bytesRead = 0
                var shouldContinue = true



            }catch (ex :Exception){

            }
        }


    }


    private fun stopAllSound(){
        synchronized(playingLines) {
            playingLines.values.forEach { line ->
                line.stop()
                line.close()
            }
            playingLines.clear()
        }
    }


    private fun loadAudioFile(fileName: String): ByteArray {
        val resourcePath = Paths.get("src/commonMain/composeResources/files/$fileName")
        if (!Files.exists(resourcePath)) {
            throw FileNotFoundException("File $fileName not found")
        }

        return FileInputStream(resourcePath.toFile()).use { it.readBytes() }
    }


}