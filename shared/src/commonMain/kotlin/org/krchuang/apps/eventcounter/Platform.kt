package org.krchuang.apps.eventcounter

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform