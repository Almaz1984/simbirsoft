package com.almaz.task1.data.repository

import com.almaz.task1.R
import com.almaz.task1.data.model.Friend

object FriendsRepository {
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
}
