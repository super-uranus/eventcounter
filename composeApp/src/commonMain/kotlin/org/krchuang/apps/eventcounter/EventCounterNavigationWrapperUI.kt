package org.krchuang.apps.eventcounter

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.jetbrains.compose.resources.stringResource


@Composable
fun EventCounterNavigationWrapperUI(
    content: @Composable () -> Unit = {}
) {
    var selectedDestination: EventCounterDestination by remember {
        mutableStateOf(EventCounterDestination.Inbox)
    }

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            EventCounterDestination.entries.forEach {
                item(
                    selected = it == selectedDestination,
                    onClick = { /*TODO update selection*/ },
                    icon = {
                        Icon(
                            imageVector = it.icon,
                            contentDescription = stringResource(it.labelRes)
                        )
                    },
                    label = {
                        Text(text = stringResource(it.labelRes))
                    },
                )
            }
        }
    ) {
        content()
    }
}