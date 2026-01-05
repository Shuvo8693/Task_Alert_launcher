package com.example.my_task_alert

import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder

class ScreenUnlockService : Service() {

    private lateinit var screenUnlockReceiver: ScreenUnlockReceiver

    override fun onCreate() {
        super.onCreate()

        screenUnlockReceiver = ScreenUnlockReceiver()

        // Register receiver for unlock events
        val filter = IntentFilter().apply {
            addAction(Intent.ACTION_USER_PRESENT)
            addAction(Intent.ACTION_SCREEN_ON)
        }
        registerReceiver(screenUnlockReceiver, filter)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(screenUnlockReceiver)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}