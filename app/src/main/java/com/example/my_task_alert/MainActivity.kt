package com.example.my_task_alert

// MainActivity.kt

import android.annotation.SuppressLint
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.PersistableBundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import androidx.core.net.toUri

class MainActivity : AppCompatActivity() {

    private lateinit var statusText: TextView
    private lateinit var toggleButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        statusText = findViewById(R.id.statusText)
        toggleButton = findViewById(R.id.toggleButton)

        // Check if the service is enabled
        updateStatus()

        toggleButton.setOnClickListener {
            // Request overlay permission if needed
            if (!Settings.canDrawOverlays(this)) {
                val intent = Intent(
                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    "package:$packageName".toUri()
                )
                startActivity(intent)
            } else {
                toggleService()
            }
        }
    }

    private fun toggleService() {
        val intent = Intent(this, ScreenUnlockService::class.java)
        if (isServiceRunning()) {
            stopService(intent)
        } else {
            startService(intent)
        }
        updateStatus()
    }

    private fun isServiceRunning(): Boolean {
        // Simple check - you can implement a more robust check
        return false
    }

    private fun updateStatus() {
        statusText.text = if (isServiceRunning()) {
            "Service is running"
        } else {
            "Service is stopped"
        }
    }
}