package com.almaz.task1.ui.news.adapters.news_filter

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.RecyclerView
import com.almaz.task1.R
import com.almaz.task1.data.model.NewsFilter

class NewsFilterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    @SuppressLint("SetTextI18n", "UseCompatLoadingForDrawables")
    fun bind(newsFilter: NewsFilter) {
        val filterCategory: TextView = itemView.findViewById(R.id.filter_item_title)
        val filterSwitch: SwitchCompat = itemView.findViewById(R.id.filter_item_switch)

        filterSwitch.setOnClickListener {
            newsFilter.isChecked = filterSwitch.isChecked
        }

        filterCategory.text = newsFilter.category
        filterSwitch.isChecked = newsFilter.isChecked
    }
}
