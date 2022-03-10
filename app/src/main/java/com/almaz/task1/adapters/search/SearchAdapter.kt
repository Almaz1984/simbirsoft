package com.almaz.task1.adapters.search

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.almaz.task1.R
import com.almaz.task1.data.model.SearchResult

class SearchAdapter(
    private val searchResultList: List<SearchResult>
) : RecyclerView.Adapter<SearchViewHolder>() {

    private var _searchResultList = searchResultList.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_search_result, parent, false)
        return SearchViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(_searchResultList[position])
    }

    override fun getItemCount(): Int {
        return _searchResultList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems() {
        _searchResultList.clear()
        _searchResultList.addAll(searchResultList)
        notifyDataSetChanged()
    }
}
