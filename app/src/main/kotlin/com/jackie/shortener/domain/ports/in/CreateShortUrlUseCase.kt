package com.jackie.shortener.domain.ports.`in`

data class CreatedLink(val shortId: String, val shortIrl: String)

interface CreateShortUrlCase {
    fun create(url: String, customAlias: String?, ownerId: String?, ttlDays: Int?): CreatedLink
}
