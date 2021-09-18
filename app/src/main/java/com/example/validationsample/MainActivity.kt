package com.example.validationsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.validationsample.example1.Example1Fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, Example1Fragment.newInstance())
                .commitNow()
        }
    }
}