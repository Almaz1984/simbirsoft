package com.almaz.task1.data.repository

import com.almaz.task1.R
import com.almaz.task1.data.model.Friend
import com.almaz.task1.data.model.HelpCategory
import com.almaz.task1.data.model.SearchResult

object Repository {
    fun getFriends() = mutableListOf(
        Friend(
            "Дмитрий Валерьевич",
            R.drawable.avatar_1
        ),
        Friend(
            "Евгений Александров",
            R.drawable.avatar_2
        ),
        Friend(
            "Виктор Кузницов",
            R.drawable.avatar_3
        )
    )

    fun getCategories() = listOf(
        HelpCategory(
            "Дети",
            R.drawable.category_kids
        ),
        HelpCategory(
            "Взрослые",
            R.drawable.category_adults
        ),
        HelpCategory(
            "Пожилые",
            R.drawable.category_elderly
        ),
        HelpCategory(
            "Животные",
            R.drawable.category_pets
        ),
        HelpCategory(
            "Мероприятия",
            R.drawable.category_events
        )

    )

    fun getSearchResults() = mutableListOf(
        SearchResult(
            "Благотворительнный фонд Алины Кабаевой",
        ),
        SearchResult(
            "Во имя жизни",
        ),
        SearchResult(
            "Благотворительнный фонд В.Потанина",
        ),
        SearchResult(
            "Детские домики",
        ),
        SearchResult(
            "Мозаика Счастья",
        ),
    )
}
