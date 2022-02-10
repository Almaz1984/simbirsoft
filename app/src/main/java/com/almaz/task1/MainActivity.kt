package com.almaz.task1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        BottomNavigationViewHelper.setupBottomNavigationView(this,
            findViewById(R.id.bottom_navigation))
    }

    companion object {
        const val HELP_MENU_INDEX = 2
    }
}
