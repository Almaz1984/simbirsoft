package com.almaz.task1.data.model

import com.almaz.task1.utils.TimeUtils.getFormattedDate
import com.almaz.task1.utils.TimeUtils.getFormattedRangeDate

data class NewsRaw(
    val id: Long?,
    val title: String?,
    val fundName: String?,
    val description: String?,
    val address: String?,
    val phone: String?,
    val date: String?,
    val dateStart: String?,
    val dateEnd: String?,
    val categories: List<String>?,
    val image: String?,
) {
    fun mapToNews(): News {
        val formattedDate = when (date) {
            null -> getFormattedRangeDate(dateStart, dateEnd)
            else -> getFormattedDate(date)
        }

        return News(
            id ?: 0,
            title ?: "",
            fundName ?: "",
            description ?: "",
            address ?: "",
            phone ?: "",
            formattedDate,
            categories ?: listOf(),
            image ?: "",
        )
    }
}
