package com.almaz.task1.data.mapper

import com.almaz.task1.data.model.News
import com.almaz.task1.data.model.NewsRaw
import com.almaz.task1.utils.TimeUtils

class NewsMapper : Mapper<NewsRaw, News> {

    override fun transform(src: NewsRaw): News {
        val formattedDate = when (src.date) {
            null -> TimeUtils.getFormattedRangeDate(src.dateStart, src.dateEnd)
            else -> TimeUtils.getFormattedDate(src.date)
        }
        return News(
            src.id ?: 0,
            src.title ?: "",
            src.fundName ?: "",
            src.description ?: "",
            src.address ?: "",
            src.phone ?: "",
            formattedDate,
            src.categories ?: listOf(),
            src.image ?: "",
        )
    }
}
