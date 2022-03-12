package com.almaz.task1

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.almaz.task1.ui.help.HelpFragment
import com.almaz.task1.ui.profile.ProfileFragment
import com.almaz.task1.ui.search.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomNavigationViewHelper {

    companion object {

        fun setupBottomNavigationView(
            context: Context,
            bottomNavigationView: BottomNavigationView
        ) {
            bottomNavigationView.apply {

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
                    }
                    transaction.commit()
                    true
                }

                selectedItemId = R.id.navigation_help
                background = ContextCompat.getDrawable(context, android.R.color.transparent)
            }
        }
    }
}
