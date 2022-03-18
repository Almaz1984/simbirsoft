package com.almaz.task1.ui.news

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.almaz.task1.R
import com.almaz.task1.data.model.NewsFilter
import com.almaz.task1.ui.DividerItemDecorationLastExcluded
import com.almaz.task1.ui.news.adapters.news_filter.NewsFiltersAdapter
import com.almaz.task1.utils.JsonHelper

class FilterFragment : Fragment() {
    private lateinit var newsFiltersAdapter: NewsFiltersAdapter
    private lateinit var preferences: SharedPreferences
    private val currentNewsFilters: MutableList<NewsFilter> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news_filter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadFilterSettings()
        setupRecyclerView(view)
        setupToolbar(view)
    }

    private fun setupToolbar(view: View) {
        view.findViewById<Toolbar>(R.id.news_filters_toolbar).apply {
            setOnMenuItemClickListener(menuItemClickListener())
            setNavigationOnClickListener { (context as FragmentActivity).onBackPressed() }
        }
    }

    private fun menuItemClickListener() = { menuItem: MenuItem ->
        when (menuItem.itemId) {
            R.id.action_confirm -> {
                saveFilterSettings()
                (context as FragmentActivity).onBackPressed()
            }
        }
        true
    }

    private fun setupRecyclerView(view: View) {

        newsFiltersAdapter = NewsFiltersAdapter()
        newsFiltersAdapter.updateItems(currentNewsFilters)

        view.findViewById<RecyclerView>(R.id.recycler_view_news_filter).apply {
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = newsFiltersAdapter
            addItemDecoration(
                DividerItemDecorationLastExcluded(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    private fun loadFilterSettings() {
        val categories = JsonHelper.getCategories(context as FragmentActivity)
        preferences = activity?.getPreferences(Context.MODE_PRIVATE) ?: return

        categories.map { it.category }.forEach {
            currentNewsFilters.add(
                NewsFilter(
                    it,
                    preferences.getBoolean(it, true)
                )
            )
        }
    }

    private fun saveFilterSettings() {
        val filtersMap = newsFiltersAdapter.getFiltersList()
        with(preferences.edit()) {
            filtersMap.forEach {
                putBoolean(it.key, it.value)
            }
            apply()
        }
    }
}
