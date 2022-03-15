package com.almaz.task1

import android.content.Context
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.fragment.app.FragmentActivity
import com.almaz.task1.ui.help.HelpFragment
import com.almaz.task1.ui.news.NewsFragment
import com.almaz.task1.ui.profile.ProfileFragment
import com.almaz.task1.ui.search.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomNavigationViewHelper {

    companion object {
        private lateinit var bottomNavigationView: BottomNavigationView

        fun setupBottomNavigationView(
            context: Context,
            bottomNavigationView: BottomNavigationView
        ) {
            this.bottomNavigationView = bottomNavigationView.apply {

                setOnItemSelectedListener { menuItem ->
                    val transaction =
                        (context as FragmentActivity).supportFragmentManager.beginTransaction()

                    when (menuItem.itemId) {
                        R.id.navigation_profile -> {
                            transaction.replace(R.id.fragment_container_view, ProfileFragment())
                        }
                        R.id.navigation_search -> {
                            transaction.replace(R.id.fragment_container_view, SearchFragment())
                        }
                        R.id.navigation_help -> {
                            transaction.replace(R.id.fragment_container_view, HelpFragment())
                        }
                        R.id.navigation_news -> {
                            transaction.replace(R.id.fragment_container_view, NewsFragment())
                        }
                    }
                    transaction.commit()
                    true
                }

                selectedItemId = R.id.navigation_help
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
