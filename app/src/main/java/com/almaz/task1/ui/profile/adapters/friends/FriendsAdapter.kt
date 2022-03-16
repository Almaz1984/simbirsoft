package com.almaz.task1.ui.profile.adapters.friends

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.almaz.task1.R
import com.almaz.task1.data.model.Friend

class FriendsAdapter(
    private val friendsList: MutableList<Friend> = mutableListOf()
) : RecyclerView.Adapter<FriendsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendsViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_friends, parent, false)
        return FriendsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FriendsViewHolder, position: Int) {
        holder.bind(friendsList[position])
    }

    override fun getItemCount() = friendsList.size
}
