package com.kmm.clappygc

enum class Platform {
    Android,
    IOS,
    Desktop,
    Web
}

expect fun getPlatform(): Platform