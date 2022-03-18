package com.almaz.task1.ui.search.adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.almaz.task1.R
import com.almaz.task1.data.model.SearchResult

class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(searchResult: SearchResult) {
        val resultTextView: TextView = itemView.findViewById(R.id.search_result)
        resultTextView.text = searchResult.result
    }
}
