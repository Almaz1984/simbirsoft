@file:Suppress("DEPRECATION")

package com.almaz.task1.ui.news

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.almaz.task1.BottomNavigationViewHelper
import com.almaz.task1.R
import com.almaz.task1.data.model.News
import com.almaz.task1.data.model.NewsFilter
import com.almaz.task1.data.repository.Repository
import com.almaz.task1.ui.news.adapters.news.NewsAdapter
import com.almaz.task1.utils.JsonHelper
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class NewsFragment : Fragment() {
    private lateinit var progressBar: View
    private lateinit var newsRecyclerView: RecyclerView
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var preferences: SharedPreferences
    private var currentNewsFilters: MutableList<NewsFilter> = mutableListOf()
    private var newsList: List<News>? = null

    private val subject = PublishSubject.create<Long>()
    private var disposable: Disposable? = null
    private val readNewsIds: MutableSet<Long> = mutableSetOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView(view)
        loadFilterSettings()

        savedInstanceState?.let {
            newsList = it.getParcelableArrayList(NEWS_KEY)
        }

        when (newsList) {
            null -> initRx()
            else -> updateNews(newsList)
        }

        disposable = subject.subscribe {
            readNewsIds.add(it)
        }
    }

    override fun onStop() {
        disposable?.dispose()
        super.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.d(NEWS_FRAGMENT_TAG, "onSaveInstanceState")
        super.onSaveInstanceState(outState)
        newsList?.let {
            outState.putParcelableArrayList(NEWS_KEY, newsList as ArrayList<out Parcelable>)
        }
    }

    private fun setupView(view: View) {
        progressBar = view.findViewById(R.id.news_progress_bar)
        setupRecyclerView(view)
        setupToolbar(view)
    }

    private fun setupRecyclerView(view: View) {
        val newsItemClickListener =
            { news: News ->
                subject.onNext(news.id)
                showNewsDetailFragment(news)
            }
        newsAdapter = NewsAdapter(newsItemClickListener)
        newsRecyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_news).apply {
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = newsAdapter
        }
    }

    private fun setupToolbar(view: View) {
        view.findViewById<Toolbar>(R.id.news_toolbar).apply {
            setOnMenuItemClickListener(menuItemClickListener())
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

    private fun menuItemClickListener() = { menuItem: MenuItem ->
        when (menuItem.itemId) {
            R.id.action_filter -> {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_view, FilterFragment())
                    .addToBackStack(null)
                    .commit()
            }
        }
        true
    }

    private fun loadFilterSettings() {
        val categories = JsonHelper.getCategories(context as FragmentActivity)
        preferences = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
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

    private fun initRx() {
        getNewsObservable().subscribe { newsList ->
            updateNews(newsList)
        }
    }

    private fun getNewsObservable() =
        Repository.loadNews().onErrorResumeNext {
            Single.just(
                JsonHelper.getNews(context as FragmentActivity)
            )
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun updateNews(newsList: List<News>?) {
        this.newsList = newsList
        newsList?.let {
            val filteredNewsList = filterNews(it)
            newsAdapter.submitList(filteredNewsList)

            val notReadNewsCount = filteredNewsList
                .filterNot { news -> readNewsIds.contains(news.id) }
                .size
            updateBadge(notReadNewsCount)
        }
        showNews()
    }

    private fun updateBadge(notReadNewsCount: Int) {
        BottomNavigationViewHelper.setNewsBadge(notReadNewsCount)
    }

    private fun filterNews(newsList: List<News>): List<News> {
        val filters = currentNewsFilters
            .filter { it.isChecked }
            .map { it.category }
        return newsList
            .filter { news -> news.categories.any { it in filters } }
            .toList()
    }

    private fun showNews() {
        newsRecyclerView.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
    }

    companion object {
        private const val NEWS_KEY = "news"
        private const val NEWS_FRAGMENT_TAG = "NewsFragment"
    }
}
