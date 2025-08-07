package org.krchuang.apps.eventcounter

import androidx.compose.runtime.mutableStateListOf
import kotlinx.datetime.TimeZone
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

class EventViewModel {
    private val _events = mutableStateListOf<Event>()
    val events: List<Event> = _events

    @OptIn(ExperimentalTime::class)
    fun addEvent(name: String) {
        if (name.isNotBlank()) {
            val now = Clock.System.now().toEpochMilliseconds()
            val timeZone = TimeZone.currentSystemDefault()
            _events.add(Event(name, now, timeZone.id))
        }
    }
}