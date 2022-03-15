package com.almaz.task1.ui.news.adapters.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.almaz.task1.R
import com.almaz.task1.data.model.News

class NewsAdapter(
    private val itemClickListener: (News) -> Unit
) : ListAdapter<News, NewsViewHolder>(NewsItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(itemView) { itemClickListener(currentList[it]) }
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    override fun getItemCount(): Int {
        return currentList.size
    }
}
