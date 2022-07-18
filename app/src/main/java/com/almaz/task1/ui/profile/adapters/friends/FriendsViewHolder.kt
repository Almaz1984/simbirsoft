package com.almaz.task1.ui.profile.adapters.friends

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.almaz.task1.R
import com.almaz.task1.data.model.Friend
import com.almaz.task1.databinding.ItemFriendsBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class FriendsViewHolder(
    private val binding: ItemFriendsBinding
) : RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n", "UseCompatLoadingForDrawables")
    fun bind(friend: Friend) {
        apply {
            binding.apply {
                Glide.with(imageViewAvatarProfile)
                    .load(friend.avatar)
                    .placeholder(R.drawable.ic_update)
                    .error(R.drawable.ic_broken)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(imageViewAvatarProfile)

                textViewNameFriend.text = friend.name
            }
        }
    }
}
