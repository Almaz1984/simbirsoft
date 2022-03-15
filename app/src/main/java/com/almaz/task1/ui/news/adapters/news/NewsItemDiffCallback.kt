package com.almaz.task1.ui.news.adapters.news

import androidx.recyclerview.widget.DiffUtil
import com.almaz.task1.data.model.News

class NewsItemDiffCallback : DiffUtil.ItemCallback<News>() {

    override fun areItemsTheSame(oldItem: News, newItem: News) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: News, newItem: News) = oldItem == newItem
}
