package com.almaz.task1.data.model

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
)
