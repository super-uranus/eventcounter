package org.kmp.testing

import kotlin.experimental.ExperimentalNativeApi

@OptIn(ExperimentalNativeApi::class)
actual fun determineCurrentRuntime(): CurrentRuntime {
    val name = Platform.osFamily.name.lowercase()
    return CurrentRuntime(name, null)
}