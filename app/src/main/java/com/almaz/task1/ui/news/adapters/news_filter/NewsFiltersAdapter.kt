package com.almaz.task1.ui.news.adapters.news_filter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.almaz.task1.R
import com.almaz.task1.data.model.NewsFilter

class NewsFiltersAdapter : RecyclerView.Adapter<NewsFilterViewHolder>() {

    private val newsFiltersList: MutableList<NewsFilter> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsFilterViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_news_filter, parent, false)
        return NewsFilterViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NewsFilterViewHolder, position: Int) {
        holder.bind(newsFiltersList[position])
    }

    override fun getItemCount() = newsFiltersList.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(newsFiltersList: List<NewsFilter>) {
        this.newsFiltersList.clear()
        this.newsFiltersList.addAll(newsFiltersList)
        notifyDataSetChanged()
    }

    fun getFiltersList(): Map<String, Boolean> =
        newsFiltersList.associate { it.category to it.isChecked }
}
