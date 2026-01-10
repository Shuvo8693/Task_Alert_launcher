package com.example.my_task_alert

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView

class LaunchActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)
        supportActionBar?.title = " TASK "

        val textView = findViewById<TextView>(R.id.launchText)
        textView.text = "App launched after unlock!"
    }
}