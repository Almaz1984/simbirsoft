package com.almaz.task1

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
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
                    }
                    transaction.commit()
                    true
                }

                menu.getItem(MainActivity.HELP_MENU_INDEX).isEnabled = false
                selectedItemId = R.id.navigation_profile
                background = ContextCompat.getDrawable(context, android.R.color.transparent)

            }
        }
    }
}
