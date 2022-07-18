@file:Suppress("DEPRECATION")

package com.almaz.task1.ui.help

import android.os.Bundle
import android.os.Parcelable
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
import com.almaz.task1.data.repository.Repository
import com.almaz.task1.ui.help.adapters.HelpAdapter
import com.almaz.task1.utils.JsonHelper
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HelpFragment : Fragment() {

    private lateinit var progressBar: View
    private lateinit var categoriesRecyclerView: RecyclerView
    private val helpAdapter = HelpAdapter()
    private var categories: List<HelpCategory>? = null

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
            null -> initRx()
            else -> updateCategories(categories)
        }
    }

    private fun initRx() {
        getHelpCategoriesObservable().subscribe { categories ->
            updateCategories(categories)
        }
    }

    private fun getHelpCategoriesObservable() =
        Repository.loadHelpCategories().onErrorResumeNext(
            Single.just(
                JsonHelper.getCategories(context as FragmentActivity)
            )
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        categories?.let {
            outState.putParcelableArrayList(
                CATEGORIES_KEY,
                categories as ArrayList<out Parcelable>
            )
        }
    }

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

    fun updateCategories(categories: List<HelpCategory>?) {
        categories?.let { helpAdapter.updateItems(categories) }
        this.categories = categories
        showCategories()
    }

    private fun showCategories() {
        categoriesRecyclerView.visibility = VISIBLE
        progressBar.visibility = GONE
    }

    companion object {
        private const val SPAN_COUNT = 2
        private const val CATEGORIES_KEY = "categories"
    }
}
