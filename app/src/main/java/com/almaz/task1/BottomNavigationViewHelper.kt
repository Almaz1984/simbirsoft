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
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomNavigationViewHelper {

    companion object {

        private lateinit var bottomNavigationView: BottomNavigationView
        private lateinit var fragmentManager: FragmentManager
        private lateinit var badge: BadgeDrawable
        private val fragments = mapOf(
            R.id.navigation_profile to ProfileFragment::class.java,
            R.id.navigation_search to SearchFragment::class.java,
            R.id.navigation_help to HelpFragment::class.java,
            R.id.navigation_news to NewsFragment::class.java,
        )

        fun setupBottomNavigationView(
            context: Context,
            bottomNavigationView: BottomNavigationView
        ) {
            fragmentManager = (context as FragmentActivity).supportFragmentManager
            this.bottomNavigationView = bottomNavigationView.apply {
                setOnItemSelectedListener(itemSelectedListener())
            }
            badge = bottomNavigationView.getOrCreateBadge(R.id.navigation_news)
            badge.isVisible = false
        }

        fun init() {
            bottomNavigationView.visibility = VISIBLE
            bottomNavigationView.selectedItemId = R.id.navigation_help
        }

        fun setNewsBadge(count: Int) {
            badge.isVisible = count > 0
            badge.number = count
        }

        private fun itemSelectedListener() = { menuItem: MenuItem ->
            fragments[menuItem.itemId]?.let { showFragment(it.newInstance()) }
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
