package com.almaz.task1.ui.search

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

class DividerItemDecorationLastExcluded(context: Context, orientation: Int) :
    DividerItemDecoration(context, orientation) {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        parent.adapter?.let { adapter ->
            when (position) {
                (adapter.itemCount) - 1 -> outRect.setEmpty()
                else -> super.getItemOffsets(outRect, view, parent, state)
            }
        }
    }
}
