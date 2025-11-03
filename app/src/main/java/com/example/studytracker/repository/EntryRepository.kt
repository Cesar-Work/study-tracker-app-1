package com.example.studytracker.repository

import android.content.SharedPreferences
import com.example.studytracker.data.DailyEntry
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Repository encapsulating persistence and retrieval of [DailyEntry] objects. Entries are stored
 * in [SharedPreferences] as JSON strings keyed by unique identifiers. A separate string set of
 * keys is maintained to quickly retrieve all existing entries. The repository exposes a
 * [StateFlow] that emits the current list of entries whenever modifications occur.
 */
class EntryRepository(private val preferences: SharedPreferences) {
    private val gson = Gson()
    private val _entries = MutableStateFlow<List<DailyEntry>>(emptyList())
    val entries: StateFlow<List<DailyEntry>> = _entries.asStateFlow()

    init {
        loadEntries()
    }

    /**
     * Reads all stored entries from preferences into memory and sorts them by date descending.
     */
    private fun loadEntries() {
        val keys = preferences.getStringSet(ENTRY_KEYS, emptySet()) ?: emptySet()
        val list = keys.mapNotNull { key ->
            preferences.getString(key, null)?.let { json ->
                runCatching { gson.fromJson(json, DailyEntry::class.java) }.getOrNull()
            }
        }.sortedByDescending { it.date }
        _entries.value = list
    }

    /**
     * Persists a new entry. Each entry is given a unique storage key based on the date and a
     * timestamp to avoid collisions for multiple entries on the same day.
     */
    fun addEntry(entry: DailyEntry) {
        val key = "entry_${entry.date}_${System.currentTimeMillis()}"
        val json = gson.toJson(entry)
        // Update the key set and store the entry.
        val keys = preferences.getStringSet(ENTRY_KEYS, mutableSetOf())?.toMutableSet() ?: mutableSetOf()
        keys.add(key)
        preferences.edit().apply {
            putString(key, json)
            putStringSet(ENTRY_KEYS, keys)
            apply()
        }
        loadEntries()
    }

    companion object {
        private const val ENTRY_KEYS = "entry_keys"
    }
}