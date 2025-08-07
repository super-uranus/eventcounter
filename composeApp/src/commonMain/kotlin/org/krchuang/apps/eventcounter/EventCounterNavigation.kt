/*
 * Copyright 2022 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.krchuang.apps.eventcounter

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material.icons.outlined.Chat
import androidx.compose.material.icons.outlined.People
import androidx.compose.ui.graphics.vector.ImageVector
import eventcounter.composeapp.generated.resources.Res
import eventcounter.composeapp.generated.resources.tab_article
import eventcounter.composeapp.generated.resources.tab_dm
import eventcounter.composeapp.generated.resources.tab_groups
import eventcounter.composeapp.generated.resources.tab_inbox
import org.jetbrains.compose.resources.StringResource

/** Navigation destinations in the app. */
enum class EventCounterDestination(
    val labelRes: StringResource,
    val icon: ImageVector,
) {
    Inbox(Res.string.tab_inbox, Icons.Default.Inbox),

    Articles(Res.string.tab_article, Icons.Default.Article),

    Messages(Res.string.tab_dm, Icons.Outlined.Chat),

    Groups(Res.string.tab_groups, Icons.Outlined.People),
}
