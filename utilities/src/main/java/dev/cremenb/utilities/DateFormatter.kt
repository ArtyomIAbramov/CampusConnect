package dev.cremenb.utilities

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

object DateFormatter {
    fun formatDDMMHHmm(date: Date): String {
        val dateFormat = SimpleDateFormat("dd.MM HH:mm")
        return dateFormat.format(date)
    }

    fun formatDateToIsoString(date: Date): String {
        val isoFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        isoFormat.timeZone = TimeZone.getTimeZone("UTC")
        return isoFormat.format(date)
    }
}