package com.colisa.monsterdex.core.network

import okhttp3.HttpUrl.Companion.toHttpUrlOrNull

fun parseOffsetFromUrl(url: String?): Int? {
    url ?: return null
    val httpUrl = url.toHttpUrlOrNull()
    return httpUrl?.queryParameter("offset")?.toIntOrNull()
}