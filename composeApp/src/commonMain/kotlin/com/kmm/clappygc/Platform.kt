package com.kmm.clappygc

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform