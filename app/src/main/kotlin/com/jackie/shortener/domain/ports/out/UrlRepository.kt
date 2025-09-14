package com.jackie.shortener.domain.ports.out

import com.jackie.shortener.domain.model.UrlEntry

interface UrlRepository {
    fun save(entry: UrlEntry): UrlEntry
    fun findById(shortId: String): UrlEntry?
    fun exists(shortId: String): Boolean
    fun incrementClicks(shortId: String): Long
}