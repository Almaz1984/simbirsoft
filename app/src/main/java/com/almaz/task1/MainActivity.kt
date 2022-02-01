package com.almaz.task1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.almaz.task1.kotlin_training.part_2.Part2

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Part2.run()
    }
}
