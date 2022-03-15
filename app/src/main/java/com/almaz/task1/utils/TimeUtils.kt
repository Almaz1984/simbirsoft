package com.almaz.task1.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

object TimeUtils {
    private const val DATE_PARSER_PATTERN = "dd-MM-yyyy"
    private const val DATE_FORMATTER_PATTERN = "dd.MM"

    fun getFormattedDate(date: String?): String {
        val dateFormat = DateTimeFormatter.ofPattern(DATE_PARSER_PATTERN)
        val parsedDate = LocalDate.parse(date, dateFormat)

        return "${parsedDate.getMonthName()} ${parsedDate.dayOfMonth}, ${parsedDate.year}"
    }

    fun getFormattedRangeDate(startDate: String?, endDate: String?): String {
        val dateFormat = DateTimeFormatter.ofPattern(DATE_PARSER_PATTERN)
        val parsedStartDate = LocalDate.parse(startDate, dateFormat)
        val parsedEndDate = LocalDate.parse(endDate, dateFormat)
        val currentDate = LocalDate.now()
        val daysLeft: ULong = ChronoUnit.DAYS.between(currentDate, parsedEndDate).toULong()
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMATTER_PATTERN)

        return "Осталось $daysLeft дней " +
            "(${parsedStartDate.format(formatter)} - ${parsedEndDate.format(formatter)})"
    }

    private fun LocalDate.getMonthName(): String {
        val months = listOf(
            "Январь",
            "Февраль",
            "Март",
            "Апрель",
            "Май",
            "Июнь",
            "Июль",
            "Август",
            "Сентябрь",
            "Октябрь",
            "Ноябрь",
            "Декабрь"
        )
        return months[monthValue - 1]
    }
}
