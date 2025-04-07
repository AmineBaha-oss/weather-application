package com.baha.todolist


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun EventListScreen() {
    val context = LocalContext.current
    val eventDao = EventDatabase.getDatabase(context).eventDao()
    val viewModel: EventViewModel = viewModel(factory = EventViewModelFactory(eventDao))
    val events by viewModel.events.collectAsState(initial = emptyList())

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "Event List",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        LazyColumn {
            items(events) { event ->
                EventCard(event = event, onEventDone = { viewModel.deleteEvent(event) })
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun EventCard(event: Event, onEventDone: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {  },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = event.name, style = MaterialTheme.typography.titleMedium)
                Text(text = event.details, style = MaterialTheme.typography.bodyMedium)
                Text(text = "Date: ${event.date}", style = MaterialTheme.typography.bodySmall)
                Text(text = "Time: ${event.time}", style = MaterialTheme.typography.bodySmall)
            }
            var checked by remember { mutableStateOf(false) }
            Checkbox(
                checked = checked,
                onCheckedChange = { isChecked ->
                    checked = isChecked
                    if (isChecked) {
                        onEventDone()
                    }
                }
            )
        }
    }
}
