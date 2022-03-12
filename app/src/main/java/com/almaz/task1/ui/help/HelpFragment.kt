package com.almaz.task1.ui.help

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.almaz.task1.R
import com.almaz.task1.adapters.help.HelpAdapter
import com.almaz.task1.data.repository.Repository

class HelpFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_help, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val helpAdapter = HelpAdapter()
        helpAdapter.updateItems(Repository.getCategories())

        view.findViewById<RecyclerView>(R.id.recycler_view_help_categories).apply {
            layoutManager =
                GridLayoutManager(context, SPAN_COUNT, GridLayoutManager.VERTICAL, false)
            adapter = helpAdapter
        }
    }

    companion object {
        const val SPAN_COUNT = 2
    }
}
