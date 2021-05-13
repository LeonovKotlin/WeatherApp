package com.example.weatherapp.db

import androidx.room.TypeConverters
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

object LocalDateConverter {
    @TypeConverters
    @JvmStatic
    fun stringToDate(str: String?) = str?.let {
        LocalDate.parse(it, DateTimeFormatter.ISO_LOCAL_DATE)
    }
    @TypeConverters
    @JvmStatic
    fun DateToString(dateTime: LocalDate?) = dateTime?.format (DateTimeFormatter.ISO_LOCAL_DATE)
}
