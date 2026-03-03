package com.example.hex_generator

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log

class HexGeneratorService : Service() {

    private var isGenerating = false
    private val handler = Handler(Looper.getMainLooper())
    
    private val generationRunnable = object : Runnable {
        override fun run() {
            if (isGenerating) {
                val code = Generator()
                val intent = Intent(GeneratorContract.ACTION_GENERATE).apply {
                    putExtra(GeneratorContract.EXTRA_CODE, code)
                }
                sendBroadcast(intent)
                Log.d("HexGeneratorService", "Broadcasting HEX: $code")
                handler.postDelayed(this, 1000)
            }
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            GeneratorContract.ACTION_SET_GENERATION_MODE -> {
                val enable = intent.getBooleanExtra(GeneratorContract.EXTRA_GENERATION_ENABLED, false)
                if (enable != isGenerating) {
                    isGenerating = enable
                    if (isGenerating) {
                        handler.post(generationRunnable)
                        Log.d("HexGeneratorService", "Generation Mode: ON")
                    } else {
                        handler.removeCallbacks(generationRunnable)
                        Log.d("HexGeneratorService", "Generation Mode: OFF")
                    }
                }
                sendStatusBroadcast()
            }
            GeneratorContract.ACTION_GET_STATUS -> {
                sendStatusBroadcast()
            }
        }
        return START_STICKY
    }

    private fun sendStatusBroadcast() {
        val intent = Intent(GeneratorContract.ACTION_STATUS_REPLY).apply {
            putExtra(GeneratorContract.EXTRA_IS_GENERATING, isGenerating)
        }
        sendBroadcast(intent)
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onDestroy() {
        isGenerating = false
        handler.removeCallbacks(generationRunnable)
        super.onDestroy()
    }
}