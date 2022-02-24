package com.almaz.task1.ui.search

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {
    private val fragments = mutableListOf<Fragment>()
    private val fragmentTitles = mutableListOf<String>()

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

    fun addFragment(title: String, fragment: Fragment) {
        fragments.add(fragment)
        fragmentTitles.add(title)
    }

    fun getTitles(): List<String> {
        return fragmentTitles
    }
}
