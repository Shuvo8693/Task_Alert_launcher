package com.example.my_task_alert

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView

class LaunchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)

        val textView = findViewById<TextView>(R.id.launchText)
        textView.text = "App launched after unlock!"
    }
}