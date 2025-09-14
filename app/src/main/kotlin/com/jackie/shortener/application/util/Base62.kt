package com.jackie.shortener.application.util

import kotlin.random.Random

object Base62 {
    private const val ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
    private val chars = ALPHABET.toCharArray()

    fun random(length: Int = 7): String {
        val sb = StringBuilder(length)
        repeat(length) { sb.append(chars[Random.nextInt(chars.size)]) }
        return sb.toString()
    }
}