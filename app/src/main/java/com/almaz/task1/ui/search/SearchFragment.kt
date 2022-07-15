package com.almaz.task1.ui.search

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.almaz.task1.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class SearchFragment : Fragment() {
    private lateinit var viewPager: ViewPager2
    private var currentFragmentPosition: Int = DEFAULT_FRAGMENT_POSITION
    private val pageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            currentFragmentPosition = position
            super.onPageSelected(position)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchViewSetup(view)
        tabLayoutSetup(view)
    }

    override fun onStart() {
        viewPager.registerOnPageChangeCallback(pageChangeCallback)
        super.onStart()
    }

    override fun onStop() {
        viewPager.unregisterOnPageChangeCallback(pageChangeCallback)
        super.onStop()
    }

    private fun searchViewSetup(view: View) {
        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = view.findViewById(R.id.search_view) as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))

        Observable.create { emitter ->
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    emitter.onNext(query)
                    searchView.clearFocus()
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    emitter.onNext(newText)
                    return false
                }
            })
        }
            .subscribeOn(Schedulers.io())
            .map { text -> text.lowercase().trim() }
            .debounce(DEBOUNCE_TIMEOUT, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                val currentFragment =
                    childFragmentManager.findFragmentByTag("f$currentFragmentPosition") as ItemViewPagerFragment
                currentFragment.applyQuery(it)
            }
    }

    private fun tabLayoutSetup(view: View) {
        val fragmentAdapter = FragmentAdapter(this)
        val tabLayout = view.findViewById(R.id.tab_layout) as TabLayout
        viewPager = view.findViewById(R.id.view_pager) as ViewPager2
        viewPager.adapter = fragmentAdapter

        enumValues<TabTitles>().forEach {
            fragmentAdapter.addFragment(
                requireContext().getString(it.title),
                ItemViewPagerFragment()
            )
        }

        TabLayoutMediator(
            tabLayout,
            viewPager
        ) { tab, position ->
            tab.text = fragmentAdapter.getTitles()[position]
        }.attach()
    }

    enum class TabTitles(val title: Int) {
        EVENTS(R.string.search_tab_label_events),
        NKO(R.string.search_tab_label_nko),
    }

    companion object {
        private const val DEBOUNCE_TIMEOUT = 500L
        private const val DEFAULT_FRAGMENT_POSITION = 0
    }
}
