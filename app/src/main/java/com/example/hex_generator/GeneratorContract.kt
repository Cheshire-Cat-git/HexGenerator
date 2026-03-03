package com.example.hex_generator

object GeneratorContract {
    const val SERVICE_PACKAGE = "com.example.hex_generator"
    const val ACTION_GENERATE = "com.example.hex_generator.ACTION_GENERATE"
    const val EXTRA_CODE = "new_hex_code"
    const val ACTION_SET_GENERATION_MODE = "com.example.hex_generator.SET_GENERATION_MODE"
    const val EXTRA_GENERATION_ENABLED = "generation_enabled"
    
    const val ACTION_GET_STATUS = "com.example.hex_generator.GET_STATUS"
    const val ACTION_STATUS_REPLY = "com.example.hex_generator.STATUS_REPLY"
    const val EXTRA_IS_GENERATING = "is_generating"
}