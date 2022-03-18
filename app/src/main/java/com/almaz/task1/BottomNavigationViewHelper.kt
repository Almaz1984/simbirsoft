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
        private val fragments = mapOf(
            R.id.navigation_profile to ProfileFragment(),
            R.id.navigation_search to SearchFragment(),
            R.id.navigation_help to HelpFragment(),
            R.id.navigation_news to NewsFragment()
        )

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
            fragments[menuItem.itemId]?.let { showFragment(it) }
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
