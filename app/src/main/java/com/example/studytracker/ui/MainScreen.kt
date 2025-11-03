package com.example.studytracker.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.compose.viewModel
import androidx.navigation.NavHostController
import com.example.studytracker.data.DailyEntry
import com.example.studytracker.viewmodel.EntryViewModel

/**
 * Displays a list of all tracked days along with a floating action button to create a new entry.
 */
@Composable
fun MainScreen(
    navController: NavHostController,
    viewModel: EntryViewModel
) {
    val entries by viewModel.entries.collectAsStateWithLifecycle(emptyList())
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Study Tracker", fontWeight = FontWeight.Bold) })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("add") }) {
                Icon(Icons.Default.Add, contentDescription = "Add new entry")
            }
        }
    ) { innerPadding ->
        if (entries.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Aucune entrée pour l'instant. Ajoutez votre première journée !")
            }
        } else {
            LazyColumn(
                modifier = Modifier.padding(innerPadding),
                contentPadding = PaddingValues(vertical = 8.dp, horizontal = 16.dp)
            ) {
                items(entries) { entry ->
                    EntryRow(entry = entry)
                }
            }
        }
    }
}

/**
 * Renders a single entry in the list. The score is calculated based on completed habits.
 */
@Composable
fun EntryRow(entry: DailyEntry) {
    // Calculate the completion score as a percentage.
    val totalTasks = 8 // breakfast, lunch, dinner, hydration, exercise, study, mindfulness, journaling
    var completed = 0
    if (entry.breakfast) completed++
    if (entry.lunch) completed++
    if (entry.dinner) completed++
    if (entry.hydrationLiters > 0) completed++
    if (entry.exerciseMinutes > 0) completed++
    if (entry.studyMinutes > 0) completed++
    if (entry.mindfulnessMinutes > 0) completed++
    if (entry.journaling) completed++
    val score = (completed.toFloat() / totalTasks * 100).toInt()

    // Visual representation of the entry
    androidx.compose.material3.Card(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .fillMaxSize(),
    ) {
        androidx.compose.foundation.layout.Column(modifier = Modifier.padding(16.dp)) {
            Text(text = entry.date, style = MaterialTheme.typography.titleMedium)
            Text(text = "Heures de sommeil: ${entry.sleepHours}")
            Text(text = "Score: $score%")
        }
    }
}