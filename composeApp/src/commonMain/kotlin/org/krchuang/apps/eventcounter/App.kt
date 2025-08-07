package org.krchuang.apps.eventcounter

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import eventcounter.composeapp.generated.resources.Res
import eventcounter.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

// Import Scaffold, TopAppBar, BottomAppBar and other necessary Material components
@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App() {
    val viewModel = remember { EventViewModel() }
    var eventName by remember { mutableStateOf("") }

    MaterialTheme {
        val topAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
        // For BottomAppBar, we'll manage its visibility manually based on scroll direction

        var isScrollingUp by remember { mutableStateOf(true) }
        var previousScrollOffset by remember { mutableStateOf(0) }
        val listState = rememberLazyListState()

        // Detect scroll direction
        LaunchedEffect(listState.firstVisibleItemScrollOffset) {
            if (previousScrollOffset < listState.firstVisibleItemScrollOffset) {
                isScrollingUp = false // Scrolling down
            } else if (previousScrollOffset > listState.firstVisibleItemScrollOffset) {
                isScrollingUp = true // Scrolling up
            }
            previousScrollOffset = listState.firstVisibleItemScrollOffset
        }
        // A more robust way to detect scroll direction for hiding/showing bottom app bar
        val isBottomBarVisible by remember {
            derivedStateOf {
                // Show if scrolling up, or if at the top of the list
                isScrollingUp || listState.firstVisibleItemIndex == 0 && listState.firstVisibleItemScrollOffset == 0
            }
        }

        Scaffold(
            modifier = Modifier.nestedScroll(topAppBarScrollBehavior.nestedScrollConnection),
            topBar = {
                TopAppBar(
                    title = { Text("My App") },
                    navigationIcon = {
                        IconButton(onClick = { /* Handle navigation icon press */ }) {
                            Icon(Icons.Filled.Menu, contentDescription = "Navigation menu")
                        }
                    },
                    actions = {
                        IconButton(onClick = { /* Handle search action */ }) {
                            Icon(Icons.Filled.Search, contentDescription = "Search")
                        }
                        IconButton(onClick = { /* Handle more actions */ }) {
                            Icon(Icons.Filled.MoreVert, contentDescription = "More options")
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    scrollBehavior = topAppBarScrollBehavior
                )
            },
            bottomBar = {
                // Animate the visibility of the BottomAppBar
                AnimatedVisibility(
                    visible = isBottomBarVisible,
                    enter = slideInVertically(initialOffsetY = { it }), // Enter from bottom
                    exit = slideOutVertically(targetOffsetY = { it })  // Exit to bottom
                ) {
                    BottomAppBar(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.primary,
                    ) {
                        IconButton(onClick = { /* Handle share action */ }) {
                            Icon(Icons.Filled.Share, contentDescription = "Share")
                        }
                        // Add other bottom app bar items here
                    }
                }
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding) // Apply the padding provided by Scaffold
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                var showContent by remember { mutableStateOf(false) }
                Button(onClick = { showContent = !showContent }) {
                    Text("Click me!")
                }
                AnimatedVisibility(showContent) {
                    val greeting = remember { Greeting().greet() }
                    Column(
                        Modifier.fillMaxWidth().padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(painterResource(Res.drawable.compose_multiplatform), null)
                        Text("Compose: $greeting")
                    }
                }
                OutlinedTextField(
                    value = eventName,
                    onValueChange = { eventName = it },
                    label = { Text("Event Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = {
                        viewModel.addEvent(eventName)
                        eventName = ""
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Add Event")
                }
                Spacer(modifier = Modifier.height(16.dp))
                // Using LazyColumn for scrollable content
                LazyColumn(
                    state = listState,
                    modifier = Modifier
                        .padding(innerPadding) // Apply the padding provided by Scaffold
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Content that makes the screen scrollable
                    // Add more items to make the list scrollable
                    items(viewModel.events) { event ->
                        Card(
                            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text("Name: ${event.name}")
                                Text("Timestamp: ${event.timestamp}")
                                Text("Time Zone: ${event.timeZone}")
                            }
                        }
                    }
                }
            }
        }
    }
}