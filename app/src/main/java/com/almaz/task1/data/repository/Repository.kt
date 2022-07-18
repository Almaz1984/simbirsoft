package com.almaz.task1.data.repository

import com.almaz.task1.R
import com.almaz.task1.data.api.Retrofit
import com.almaz.task1.data.model.Friend
import com.almaz.task1.data.model.SearchResult

object Repository {

    private val api = Retrofit.getApi()

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

    fun loadHelpCategories() = api.loadHelpCategories()

    fun loadNews() = api.loadNews()
}
