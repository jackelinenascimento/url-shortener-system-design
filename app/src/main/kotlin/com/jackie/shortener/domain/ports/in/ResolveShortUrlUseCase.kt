package com.jackie.shortener.domain.ports.`in`

interface ResolveShortUrlUseCase {
    fun resolve(shortId: String): String?
}