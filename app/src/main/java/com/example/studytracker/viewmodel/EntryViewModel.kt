package com.example.studytracker.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import com.example.studytracker.data.DailyEntry
import com.example.studytracker.repository.EntryRepository
import kotlinx.coroutines.flow.StateFlow

/**
 * ViewModel that exposes tracked entries to the UI and mediates between the UI and the
 * [EntryRepository]. Using [AndroidViewModel] gives access to the application context for
 * obtaining a [SharedPreferences] instance.
 */
class EntryViewModel(application: Application) : AndroidViewModel(application) {
    private val preferences = application.getSharedPreferences("study_tracker", Context.MODE_PRIVATE)
    private val repository = EntryRepository(preferences)

    /** Exposes a [StateFlow] of the current list of entries. */
    val entries: StateFlow<List<DailyEntry>> = repository.entries

    /** Adds a new daily entry to the repository. */
    fun addEntry(entry: DailyEntry) {
        repository.addEntry(entry)
    }
}