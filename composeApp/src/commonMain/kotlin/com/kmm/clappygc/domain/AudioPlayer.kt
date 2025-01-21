package com.kmm.clappygc.domain


//
// Created by Code For Android on 21/01/25.
// Copyright (c) 2025 CFA. All rights reserved.
//

expect class AudioPlayer {
    fun playGameOverSound()
    fun playJumpSound()
    fun playFallingSound()
    fun stopFallingSound()
    fun playGameSoundInLoop()
    fun stopGameSound()
    fun release()
}


val soundResList = listOf(
    "files/falling.wav",
    "files/game_over.wav",
    "files/game_sound.wav",
    "files/jump.wav"
)