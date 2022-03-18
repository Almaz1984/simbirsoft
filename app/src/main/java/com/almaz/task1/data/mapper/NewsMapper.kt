package com.almaz.task1.data.mapper

import com.almaz.task1.data.model.News
import com.almaz.task1.data.model.NewsRaw
import com.almaz.task1.utils.TimeUtils

class NewsMapper : (NewsRaw) -> News() {

    override fun invoke(newsRaw: NewsRaw): News {
        val formattedDate = when (newsRaw.date) {
            null -> TimeUtils.getFormattedRangeDate(newsRaw.dateStart, newsRaw.dateEnd)
            else -> TimeUtils.getFormattedDate(newsRaw.date)
        }
        return News(
            newsRaw.id ?: 0,
            newsRaw.title ?: "",
            newsRaw.fundName ?: "",
            newsRaw.description ?: "",
            newsRaw.address ?: "",
            newsRaw.phone ?: "",
            formattedDate,
            newsRaw.categories ?: listOf(),
            newsRaw.image ?: "",
        )
    }
}
