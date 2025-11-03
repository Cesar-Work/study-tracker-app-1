package com.example.studytracker.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.studytracker.data.DailyEntry
import com.example.studytracker.viewmodel.EntryViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * Screen for adding a new daily entry. Contains a scrollable form with various fields to track
 * lifestyle habits like sleep, meals, hydration, exercise, study sessions, mindfulness, and journaling.
 */
@Composable
fun AddEntryScreen(navController: NavHostController, viewModel: EntryViewModel) {
    var sleepHoursText by remember { mutableStateOf("") }
    var breakfast by remember { mutableStateOf(false) }
    var lunch by remember { mutableStateOf(false) }
    var dinner by remember { mutableStateOf(false) }
    var hydrationText by remember { mutableStateOf("") }
    var exerciseMinutesText by remember { mutableStateOf("") }
    var studyMinutesText by remember { mutableStateOf("") }
    var mindfulnessMinutesText by remember { mutableStateOf("") }
    var journaling by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Ajouter une entrée") },
                navigationIcon = {
                    androidx.compose.material3.IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Retour",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Sleep hours input
            OutlinedTextField(
                value = sleepHoursText,
                onValueChange = { sleepHoursText = it },
                label = { Text("Heures de sommeil") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            // Breakfast
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Petit déjeuner équilibré")
                Checkbox(
                    checked = breakfast,
                    onCheckedChange = { breakfast = it }
                )
            }
            // Lunch
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Déjeuner équilibré")
                Checkbox(
                    checked = lunch,
                    onCheckedChange = { lunch = it }
                )
            }
            // Dinner
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Dîner équilibré")
                Checkbox(
                    checked = dinner,
                    onCheckedChange = { dinner = it }
                )
            }
            // Hydration
            OutlinedTextField(
                value = hydrationText,
                onValueChange = { hydrationText = it },
                label = { Text("Hydratation (L)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            // Exercise minutes
            OutlinedTextField(
                value = exerciseMinutesText,
                onValueChange = { exerciseMinutesText = it },
                label = { Text("Minutes d'exercice") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            // Study minutes
            OutlinedTextField(
                value = studyMinutesText,
                onValueChange = { studyMinutesText = it },
                label = { Text("Minutes d'étude") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            // Mindfulness minutes
            OutlinedTextField(
                value = mindfulnessMinutesText,
                onValueChange = { mindfulnessMinutesText = it },
                label = { Text("Minutes de pleine conscience") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            // Journaling
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Journal quotidien")
                Checkbox(
                    checked = journaling,
                    onCheckedChange = { journaling = it }
                )
            }
            Spacer(modifier = Modifier.size(8.dp))
            Button(
                onClick = {
                    val date = LocalDate.now().format(DateTimeFormatter.ISO_DATE)
                    val entry = DailyEntry(
                        date = date,
                        sleepHours = sleepHoursText.toIntOrNull() ?: 0,
                        breakfast = breakfast,
                        lunch = lunch,
                        dinner = dinner,
                        hydrationLiters = hydrationText.toFloatOrNull() ?: 0f,
                        exerciseMinutes = exerciseMinutesText.toIntOrNull() ?: 0,
                        studyMinutes = studyMinutesText.toIntOrNull() ?: 0,
                        mindfulnessMinutes = mindfulnessMinutesText.toIntOrNull() ?: 0,
                        journaling = journaling
                    )
                    viewModel.addEntry(entry)
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Enregistrer")
            }
        }
    }
}