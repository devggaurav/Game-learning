package com.kmm.clappygc.domain

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue


//
// Created by Code For Android on 14/01/25.
// Copyright (c) 2025 CFA. All rights reserved.
//


data class Game(
    val screenWidth: Int = 0,
    val screenHeight: Int = 0,
    val gravity: Float = 0.7f,
    val beeJumpImpulse: Float = -12f,
    val beeMaxVelocity: Float = 25f
) {
    var status by mutableStateOf(GameStatus.Idle)
        private set


    var beeVelocity by mutableStateOf(0f)
        private set

    var bee by mutableStateOf(
        Bee(
            x = (screenWidth / 4).toFloat(),
            y = (screenHeight / 2).toFloat(),
        )
    )
        private set


    fun start() {
        status = GameStatus.Started
    }

    fun gameOver() {
        status = GameStatus.Over
    }

    fun jump() {
        beeVelocity = beeJumpImpulse
    }


    fun updateGameProgress() {
        beeVelocity = (beeVelocity + gravity).coerceIn(-beeMaxVelocity, beeMaxVelocity)
        bee = bee.copy(y = bee.y + beeVelocity)
    }


}