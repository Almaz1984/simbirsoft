package com.almaz.task1.ui.news

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.almaz.task1.R
import com.almaz.task1.data.model.News
import com.almaz.task1.data.model.NewsFilter
import com.almaz.task1.ui.news.adapters.news.NewsAdapter
import com.almaz.task1.utils.JsonHelper

class NewsFragment : Fragment() {
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var preferences: SharedPreferences
    private var currentNewsFilters: MutableList<NewsFilter> = mutableListOf()
    private val onSharedPreferenceChangeListener =
        SharedPreferences.OnSharedPreferenceChangeListener { _, _ ->
            loadFilterSettings()
            updateNews()
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preferences = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        preferences.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener)
        setupRecyclerView(view)
        setupToolbar(view)
        loadFilterSettings()
        updateNews()
    }

    override fun onDestroy() {
        super.onDestroy()
        preferences.unregisterOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener)
    }

    private fun setupRecyclerView(view: View) {
        val newsItemClickListener =
            { news: News ->
                showNewsDetailFragment(news)
            }
        newsAdapter = NewsAdapter(newsItemClickListener)
        view.findViewById<RecyclerView>(R.id.recycler_view_news).apply {
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = newsAdapter
        }
    }

    private fun updateNews() {
        var newsList = JsonHelper.getNews(context as FragmentActivity)
        val filters = currentNewsFilters
            .filter { it.isChecked }
            .map { it.category }

        newsList = newsList
            .filter { news -> news.categories.any { it in filters } }
            .toList()

        newsAdapter.submitList(newsList)
    }

    private fun setupToolbar(view: View) {
        view.findViewById<Toolbar>(R.id.news_toolbar).apply {
            setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.action_filter -> {
                        parentFragmentManager.beginTransaction()
                            .add(R.id.fragment_container_view, FilterFragment())
                            .addToBackStack(null)
                            .commit()
                    }
                }
                true
            }
        }
    }

    private fun loadFilterSettings() {
        val categories = JsonHelper.getCategories(context as FragmentActivity)

        currentNewsFilters.clear()
        categories.map { it.category }.forEach {
            currentNewsFilters.add(
                NewsFilter(
                    it,
                    preferences.getBoolean(it, true)
                )
            )
        }
    }

    private fun showNewsDetailFragment(news: News) {
        val newsDetailFragment = NewsDetailFragment.newInstance(news)
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container_view, newsDetailFragment)
            .addToBackStack(null)
            .commit()
    }
}
