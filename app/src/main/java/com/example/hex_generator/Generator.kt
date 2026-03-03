package com.example.hex_generator

import kotlin.random.Random

object Generator {
   private val cachedCodes = mutableSetOf<String>()

    operator fun invoke(): String {
        var code : String
        do{
            val chars = "0123456789ABCDEF"
            val result = StringBuilder()
            repeat(24) {
                result.append(chars[Random.nextInt(chars.length)])
            }
            code = result.toString()
        } while (cachedCodes.contains(code))
        cachedCodes.add(code)
        return code
    }
}