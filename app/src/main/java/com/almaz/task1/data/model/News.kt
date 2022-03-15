package com.almaz.task1.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class News(
    val id: Long,
    val title: String,
    val fundName: String,
    val description: String,
    val address: String,
    val phone: String,
    val date: String,
    val categories: List<String>,
    val image: String,
) : Parcelable
