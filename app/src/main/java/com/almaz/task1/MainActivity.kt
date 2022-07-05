package com.almaz.task1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.almaz.task1.databinding.ActivityMainBinding
import com.almaz.task1.ui.authorization.AuthorizationFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        when (savedInstanceState) {
            null -> supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_container_view, AuthorizationFragment())
                commit()
            }
        }

        BottomNavigationViewHelper.setupBottomNavigationView(
            this,
            findViewById(R.id.bottom_navigation)
        )
    }
}
