@file:Suppress("DEPRECATION")

package com.almaz.task1.ui.news

import android.content.Context
import android.content.SharedPreferences
import android.os.AsyncTask
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
import com.almaz.task1.R
import com.almaz.task1.data.model.News
import com.almaz.task1.data.model.NewsFilter
import com.almaz.task1.ui.news.adapters.news.NewsAdapter
import com.almaz.task1.utils.JsonHelper
import com.almaz.task1.utils.NewsAsyncTask
import java.lang.ref.WeakReference

class NewsFragment : Fragment() {
    private lateinit var progressBar: View
    private lateinit var newsRecyclerView: RecyclerView
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var preferences: SharedPreferences
    private var currentNewsFilters: MutableList<NewsFilter> = mutableListOf()
    private var newsList: List<News>? = null
    private var asyncTask: NewsAsyncTask? = null
//    private var newsTaskExecutor: NewsTaskExecutor? = null

//    private val newsReceiver = object : BroadcastReceiver() {
//        override fun onReceive(context: Context?, intent: Intent?) {
//            when (intent?.action) {
//                NewsIntentService.ACTION_NEWS_UPDATE -> {
//                    newsList =
//                        intent.getParcelableArrayListExtra(NewsIntentService.NEWS_KEY_OUT)
//                    newsList?.let { updateNews(it) }
//                }
//            }
//        }
//    }

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
            null -> initAsyncTask()
//            null -> initService()
//            null -> initExecutor()
            else -> updateNews(newsList)
        }
    }

//    override fun onStart() {
//        super.onStart()
//        registerReceiver()
//    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.d("TAG", "onSaveInstanceState")
        super.onSaveInstanceState(outState)
        newsList?.let {
            outState.putParcelableArrayList(NEWS_KEY, newsList as ArrayList<out Parcelable>)
        }
    }

//    override fun onStop() {
//        super.onStop()
//        context?.unregisterReceiver(newsReceiver)
//    }

    private fun setupView(view: View) {
        progressBar = view.findViewById(R.id.news_progress_bar)
        setupRecyclerView(view)
        setupToolbar(view)
    }

    private fun setupRecyclerView(view: View) {
        val newsItemClickListener =
            { news: News ->
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

    private fun initAsyncTask() {
        asyncTask = NewsAsyncTask.getInstance(WeakReference(this))
        when (asyncTask?.status) {
            AsyncTask.Status.PENDING -> asyncTask?.execute()
            else -> return
        }
    }

//    private fun initExecutor() {
//        newsTaskExecutor = NewsTaskExecutor.getInstance(WeakReference(this))
//        newsTaskExecutor?.execute()
//    }
//
//    private fun initService() {
//        Intent(activity, NewsIntentService::class.java).also {
//            activity?.startService(it)
//        }
//    }

//    private fun registerReceiver() {
//        val intentFilter = IntentFilter(
//            NewsIntentService.ACTION_NEWS_UPDATE
//        )
//        intentFilter.addCategory(Intent.CATEGORY_DEFAULT)
//        context?.registerReceiver(newsReceiver, intentFilter)
//    }

    fun updateNews(newsList: List<News>?) {
        this.newsList = newsList
        newsList?.let {
            val filteredNewsList = filterNews(it)
            newsAdapter.submitList(filteredNewsList)
        }
        showNews()
//        newsTaskExecutor?.shutdown()
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
    }
}
