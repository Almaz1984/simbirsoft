package com.almaz.task1.adapter

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.almaz.task1.R
import com.almaz.task1.data.model.Friend

class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    @SuppressLint("SetTextI18n", "UseCompatLoadingForDrawables")
    fun bind(
        friend: Friend
    ) {
        val friendName: TextView = itemView.findViewById(R.id.text_view_name_friend)
        val photo: ImageView = itemView.findViewById(R.id.text_view_avatar_profile)
        friendName.text = friend.name
        photo.setImageDrawable(itemView.context.getDrawable(friend.avatar))
    }
}
