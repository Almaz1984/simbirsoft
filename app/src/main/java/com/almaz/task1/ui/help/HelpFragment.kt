@file:Suppress("DEPRECATION")

package com.almaz.task1.ui.help

import android.os.AsyncTask
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.almaz.task1.R
import com.almaz.task1.data.model.HelpCategory
import com.almaz.task1.ui.help.adapters.HelpAdapter
import com.almaz.task1.utils.CategoriesAsyncTask
import com.almaz.task1.utils.JsonHelper
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.lang.ref.WeakReference

class HelpFragment : Fragment() {

    private lateinit var progressBar: View
    private lateinit var categoriesRecyclerView: RecyclerView
    private val helpAdapter = HelpAdapter()
    private var categories: List<HelpCategory>? = null
    private var categoriesAsyncTask: CategoriesAsyncTask? = null
//    private var categoriesTaskExecutor: CategoriesTaskExecutor? = null

//    private val categoriesReceiver = object : BroadcastReceiver() {
//        override fun onReceive(context: Context?, intent: Intent?) {
//            when (intent?.action) {
//                CategoriesIntentService.ACTION_CATEGORIES_UPDATE -> {
//                    categories =
//                        intent.getParcelableArrayListExtra(CategoriesIntentService.CATEGORIES_KEY_OUT)
//                    categories?.let { updateCategories(it) }
//                }
//            }
//        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_help, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView(view)

        savedInstanceState?.let {
            categories = it.getParcelableArrayList(CATEGORIES_KEY)
        }

        when (categories) {
//            null -> initAsyncTask()
//            null -> initService()
//            null -> initExecutor()
            null -> initRx()
            else -> updateCategories(categories)
        }
    }

    private fun initRx() {
        val categories: MutableList<HelpCategory> = mutableListOf()

        getNewsObservable()
            .subscribe({
                categories.add(it)
            }, {
                Log.d(HELP_FRAGMENT_TAG, "Error occurred: ${it.message}")
            }, {
                updateCategories(categories)
            })
    }

    private fun getNewsObservable() =
        Observable.fromIterable(JsonHelper.getCategories(context as FragmentActivity))
            .doOnNext { Thread.sleep(SLEEP_TIME) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

//    override fun onStart() {
//        super.onStart()
//        registerReceiver()
//    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        categories?.let {
            outState.putParcelableArrayList(
                CATEGORIES_KEY,
                categories as ArrayList<out Parcelable>
            )
        }
    }

//    override fun onStop() {
//        super.onStop()
//        context?.unregisterReceiver(categoriesReceiver)
//    }

    private fun setupView(view: View) {
        progressBar = view.findViewById(R.id.help_category_progress_bar)
        setupRecyclerView(view)
    }

    private fun setupRecyclerView(view: View) {
        categoriesRecyclerView = view.findViewById(R.id.recycler_view_help_categories)
        categoriesRecyclerView.apply {
            layoutManager =
                GridLayoutManager(context, SPAN_COUNT, GridLayoutManager.VERTICAL, false)
            adapter = helpAdapter
        }
    }

    private fun initAsyncTask() {
        categoriesAsyncTask = CategoriesAsyncTask.getInstance(WeakReference(this))
        when (categoriesAsyncTask?.status) {
            AsyncTask.Status.PENDING -> categoriesAsyncTask?.execute()
            else -> return
        }
    }

//    private fun initExecutor() {
//        categoriesTaskExecutor = CategoriesTaskExecutor.getInstance(WeakReference(this))
//        categoriesTaskExecutor?.execute()
//    }

//    private fun initService() {
//        Intent(activity, NewsIntentService::class.java).also {
//            activity?.startService(it)
//        }
//    }

//        private fun registerReceiver() {
//        val intentFilter = IntentFilter(
//            CategoriesIntentService.ACTION_UPDATE
//        )
//        intentFilter.addCategory(Intent.CATEGORY_DEFAULT)
//        context?.registerReceiver(categoriesReceiver, intentFilter)
//    }

    fun updateCategories(categories: List<HelpCategory>?) {
        categories?.let { helpAdapter.updateItems(categories) }
        this.categories = categories
        showCategories()
//        categoriesTaskExecutor?.shutdown()
    }

    private fun showCategories() {
        categoriesRecyclerView.visibility = VISIBLE
        progressBar.visibility = GONE
    }

    companion object {
        private const val SPAN_COUNT = 2
        private const val SLEEP_TIME = 1000L
        private const val CATEGORIES_KEY = "categories"
        private const val HELP_FRAGMENT_TAG = "help_fragment"
    }
}
