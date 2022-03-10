package com.almaz.task1.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.almaz.task1.R
import com.almaz.task1.adapters.search.SearchAdapter
import com.almaz.task1.data.repository.Repository

class ItemViewPagerFragment : Fragment() {
    private val searchResultList = Repository.getSearchResults()
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.item_view_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchAdapter = SearchAdapter()
        searchAdapter.updateItems(searchResultList)

        recyclerView = view.findViewById<RecyclerView>(R.id.rv_search_result).apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(
                DividerItemDecorationLastExcluded(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
            adapter = searchAdapter
        }
    }

    override fun onPause() {
        super.onPause()
        searchResultList.shuffle()
        searchAdapter.updateItems(searchResultList)
    }
}
