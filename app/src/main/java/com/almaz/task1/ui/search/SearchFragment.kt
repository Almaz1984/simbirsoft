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

class SearchFragment : Fragment() {

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

    private fun searchViewSetup(view: View) {
        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = view.findViewById(R.id.search_view) as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
    }

    private fun tabLayoutSetup(view: View) {
        val fragmentAdapter = FragmentAdapter(this)
        val viewPager = view.findViewById(R.id.view_pager) as ViewPager2
        val tabLayout = view.findViewById(R.id.tab_layout) as TabLayout
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
}
