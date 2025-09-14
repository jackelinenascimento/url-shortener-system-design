package com.jackie.shortener.domain.model

data class UrlEntry(
    val shortId: String,
    val originalUrl: String,
    val ownerId: String? = null,
    val createdAt: Long,
    val expiresAt: Long? = null,
    val clicks: Long = 0
)
