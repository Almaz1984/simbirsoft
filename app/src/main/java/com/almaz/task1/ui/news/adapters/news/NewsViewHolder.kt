package com.almaz.task1.ui.news.adapters.news

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.almaz.task1.R
import com.almaz.task1.data.model.News

class NewsViewHolder(
    itemView: View,
    onItemClicked: (Int) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    init {
        itemView.setOnClickListener {
            onItemClicked(adapterPosition)
        }
    }

    @SuppressLint("SetTextI18n", "UseCompatLoadingForDrawables")
    fun bind(news: News) {
        val title: TextView = itemView.findViewById(R.id.news_title)
        val description: TextView = itemView.findViewById(R.id.news_description)
        val image: ImageView = itemView.findViewById(R.id.news_image)
        val date: TextView = itemView.findViewById(R.id.news_date)
        title.text = news.title
        description.text = news.description
        image.setImageDrawable(itemView.context.getDrawable(news.image))
        date.text = news.date
    }

    private fun Context.getDrawable(image: String): Drawable? {
        val id = resources.getIdentifier(image, "drawable", packageName)
        return ContextCompat.getDrawable(this, id)
    }
}
