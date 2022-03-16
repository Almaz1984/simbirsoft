package com.almaz.task1.ui.help.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.almaz.task1.R
import com.almaz.task1.data.model.HelpCategory

class HelpAdapter : RecyclerView.Adapter<HelpViewHolder>() {

    private val categoryList: MutableList<HelpCategory> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HelpViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_help, parent, false)
        return HelpViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: HelpViewHolder, position: Int) {
        holder.bind(categoryList[position])
    }

    override fun getItemCount() = categoryList.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(categoryList: List<HelpCategory>) {
        this.categoryList.clear()
        this.categoryList.addAll(categoryList)
        notifyDataSetChanged()
    }
}
