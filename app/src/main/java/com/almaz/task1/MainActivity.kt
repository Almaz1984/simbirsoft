package com.almaz.task1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupBottomNavigationView()
    }

    private fun setupBottomNavigationView() {
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.apply {

            setOnItemSelectedListener { menuItem ->
                val transaction = supportFragmentManager.beginTransaction()
                when (menuItem.itemId) {
                    R.id.navigation_profile -> {
                        transaction.replace(R.id.fragment_container_view, ProfileFragment())
                    }
                }
                transaction.commit()
                true
            }

            menu.getItem(HELP_MENU_INDEX).isEnabled = false
            selectedItemId = R.id.navigation_profile
            background = null
        }
    }

    companion object {
        const val HELP_MENU_INDEX = 2
    }
}
