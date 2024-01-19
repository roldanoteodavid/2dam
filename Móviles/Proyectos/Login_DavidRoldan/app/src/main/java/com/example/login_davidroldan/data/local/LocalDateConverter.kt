package com.example.login_davidroldan.data.local

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object LocalDateConverter {
    private val formatter: DateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE

    @TypeConverter
    @JvmStatic
    fun toLong(date: LocalDate?): Long? {
        return date?.let { it.toEpochDay() }
    }

    @TypeConverter
    @JvmStatic
    fun toLocalDate(value: Long?): LocalDate? {
        return value?.let { LocalDate.ofEpochDay(it) }
    }
}
