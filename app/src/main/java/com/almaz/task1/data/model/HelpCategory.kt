package com.almaz.task1.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HelpCategory(
    val category: String,
    val picture: String,
) : Parcelable
