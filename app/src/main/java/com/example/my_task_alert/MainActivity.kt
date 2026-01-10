package com.example.my_task_alert

// MainActivity.kt

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
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

        supportActionBar?.title = " TASK "

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
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(intent)
            } else {
                startService(intent)
            }
        }
        updateStatus()
    }

    private fun isServiceRunning(): Boolean {
        val manager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        @Suppress("DEPRECATION")
        for (service in manager.getRunningServices(Int.MAX_VALUE)) {
            if (ScreenUnlockService::class.java.name == service.service.className) {
                return true
            }
        }
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
