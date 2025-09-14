package com.jackie.shortener.domain.ports.`in`

import com.jackie.shortener.domain.model.UrlEntry

interface GetUrlMetadataUseCase {
    fun get(shortId: String): UrlEntry?
}