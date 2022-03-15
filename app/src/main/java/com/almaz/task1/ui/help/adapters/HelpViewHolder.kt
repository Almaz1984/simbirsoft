package com.almaz.task1.ui.help.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.almaz.task1.R
import com.almaz.task1.data.model.HelpCategory

class HelpViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    @SuppressLint("SetTextI18n", "UseCompatLoadingForDrawables")
    fun bind(helpCategory: HelpCategory) {
        val title: TextView = itemView.findViewById(R.id.help_category_title)
        val picture: ImageView = itemView.findViewById(R.id.help_category_image)
        title.text = helpCategory.category
        picture.setImageDrawable((itemView.context.getDrawable(helpCategory.picture)))
    }

    private fun Context.getDrawable(image: String): Drawable? {
        val id = resources.getIdentifier(image, "drawable", packageName)
        return ContextCompat.getDrawable(this, id)
    }
}
