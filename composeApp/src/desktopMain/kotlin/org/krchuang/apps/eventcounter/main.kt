package org.krchuang.apps.eventcounter

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "EventCounter",
    ) {
        App()
    }
}