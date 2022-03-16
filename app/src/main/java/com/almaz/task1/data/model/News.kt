package com.almaz.task1.data.model

import android.os.Parcel
import android.os.Parcelable

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
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.createStringArrayList() ?: listOf(),
        parcel.readString() ?: "",
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(title)
        parcel.writeString(fundName)
        parcel.writeString(description)
        parcel.writeString(address)
        parcel.writeString(phone)
        parcel.writeString(date)
        parcel.writeStringList(categories)
        parcel.writeString(image)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<News> {

        override fun createFromParcel(parcel: Parcel) = News(parcel)
        override fun newArray(size: Int) = arrayOfNulls<News>(size)
    }
}
