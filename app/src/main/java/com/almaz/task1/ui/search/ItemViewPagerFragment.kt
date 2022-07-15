package com.almaz.task1.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.almaz.task1.R
import com.almaz.task1.data.repository.Repository
import com.almaz.task1.ui.DividerItemDecorationLastExcluded
import com.almaz.task1.ui.search.adapters.SearchAdapter

class ItemViewPagerFragment : Fragment() {
    private val searchResultList = Repository.getSearchResults()
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var emptyStateView: ConstraintLayout

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

        emptyStateView = view.findViewById(R.id.empty_state_view)
    }

    fun applyQuery(query: String) {
        when (query.isEmpty()) {
            true -> emptyStateView.visibility = VISIBLE
            false -> emptyStateView.visibility = GONE
        }

        searchAdapter.updateItems(
            searchResultList.filter { searchResult ->
                searchResult.result.contains(
                    query,
                    true
                )
            }
        )
    }
}
