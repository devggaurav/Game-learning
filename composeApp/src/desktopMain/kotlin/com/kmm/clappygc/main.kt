package com.kmm.clappygc

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.kmm.clappygc.di.initializeKoin

fun main() = application {
    initializeKoin()
    Window(
        onCloseRequest = ::exitApplication,
        title = "ClappyGameLearning",
    ) {
        App()
    }
}