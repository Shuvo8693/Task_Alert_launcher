package com.example.my_task_alert

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class ScreenUnlockReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            Intent.ACTION_USER_PRESENT -> {
                // Screen was unlocked
                launchApp(context)
            }
            Intent.ACTION_SCREEN_ON -> {
                // Screen turned on (may still be locked)
            }
        }
    }

    private fun launchApp(context: Context) {
        val launchIntent = Intent(context, LaunchActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        }
        context.startActivity(launchIntent)
    }
}