package com.example.studytracker.data

/**
 * Represents a single day's tracked lifestyle information. Each field corresponds to a habit or
 * metric from the optimized lifestyle protocol. Fields are simple primitives to facilitate
 * lightweight storage and serialization via JSON.
 *
 * @param date ISO‑8601 formatted string (e.g. "2025-11-03") identifying the day of this entry.
 * @param sleepHours Total hours of sleep for the night preceding this date.
 * @param breakfast Whether a balanced breakfast was consumed.
 * @param lunch Whether a balanced lunch was consumed.
 * @param dinner Whether a balanced dinner was consumed.
 * @param hydrationLiters Total liters of water consumed throughout the day.
 * @param exerciseMinutes Total minutes of exercise completed.
 * @param studyMinutes Total minutes of focused study completed.
 * @param mindfulnessMinutes Total minutes spent on mindfulness or meditation.
 * @param journaling Whether a daily journal entry was completed.
 */
data class DailyEntry(
    val date: String,
    val sleepHours: Int,
    val breakfast: Boolean,
    val lunch: Boolean,
    val dinner: Boolean,
    val hydrationLiters: Float,
    val exerciseMinutes: Int,
    val studyMinutes: Int,
    val mindfulnessMinutes: Int,
    val journaling: Boolean
)