package com.almaz.task1

import android.content.Context
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.almaz.task1.ui.help.HelpFragment
import com.almaz.task1.ui.news.NewsFragment
import com.almaz.task1.ui.profile.ProfileFragment
import com.almaz.task1.ui.search.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomNavigationViewHelper {

    companion object {

        private lateinit var bottomNavigationView: BottomNavigationView
        private lateinit var fragmentManager: FragmentManager

        fun setupBottomNavigationView(
            context: Context,
            bottomNavigationView: BottomNavigationView
        ) {
            fragmentManager = (context as FragmentActivity).supportFragmentManager
            this.bottomNavigationView = bottomNavigationView.apply {
                setOnItemSelectedListener(itemSelectedListener())
                selectedItemId = R.id.navigation_help
            }
        }

        private fun itemSelectedListener() = { menuItem: MenuItem ->

            when (menuItem.itemId) {
                R.id.navigation_profile -> showFragment(ProfileFragment())
                R.id.navigation_search -> showFragment(SearchFragment())
                R.id.navigation_help -> showFragment(HelpFragment())
                R.id.navigation_news -> showFragment(NewsFragment())
            }
            true
        }

        private fun showFragment(fragment: Fragment) {
            fragmentManager.beginTransaction().apply {
                replace(R.id.fragment_container_view, fragment)
                commit()
            }
        }

        fun hideBottomNavigationView() {
            bottomNavigationView.visibility = GONE
        }

        fun showBottomNavigationView() {
            bottomNavigationView.visibility = VISIBLE
        }
    }
}
