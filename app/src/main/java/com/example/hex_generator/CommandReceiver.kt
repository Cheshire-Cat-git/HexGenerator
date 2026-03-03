package com.example.hex_generator

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class CommandReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val serviceIntent = Intent(context, HexGeneratorService::class.java).apply {
            action = intent.action
            putExtras(intent)
        }
        context.startService(serviceIntent)
    }
}