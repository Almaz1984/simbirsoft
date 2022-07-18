package com.almaz.task1.ui.profile.adapters.friends

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.almaz.task1.data.model.Friend
import com.almaz.task1.databinding.ItemFriendsBinding

class FriendsAdapter(
    private val friendsList: MutableList<Friend> = mutableListOf()
) : RecyclerView.Adapter<FriendsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendsViewHolder {
        return FriendsViewHolder(
            ItemFriendsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FriendsViewHolder, position: Int) {
        holder.bind(friendsList[position])
    }

    override fun getItemCount() = friendsList.size
}
