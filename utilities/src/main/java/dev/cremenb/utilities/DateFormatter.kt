package dev.cremenb.utilities

import java.text.SimpleDateFormat
import java.util.Date

object DateFormatter {
    fun formatDDMMHHmm(date: Date): String {
        val dateFormat = SimpleDateFormat("dd.MM HH:mm")
        return dateFormat.format(date)
    }
}