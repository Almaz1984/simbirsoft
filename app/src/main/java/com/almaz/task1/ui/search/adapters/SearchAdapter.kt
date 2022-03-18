package com.almaz.task1.ui.search.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.almaz.task1.R
import com.almaz.task1.data.model.SearchResult

class SearchAdapter : RecyclerView.Adapter<SearchViewHolder>() {

    private val searchResultList: MutableList<SearchResult> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_search_result, parent, false)
        return SearchViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(searchResultList[position])
    }

    override fun getItemCount() = searchResultList.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(searchResultList: List<SearchResult>) {
        this.searchResultList.clear()
        this.searchResultList.addAll(searchResultList)
        notifyDataSetChanged()
    }
}
