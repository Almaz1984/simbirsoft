package com.almaz.task1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.almaz.task1.kotlin_training.part_1.Part1

const val TAG = "MAIN_ACTIVITY"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Part1.run()
    }
}
