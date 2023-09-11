package com.colisa.monsterdex.core.model

import java.util.UUID

data class SnackMessage(
    val id: String = UUID.randomUUID().toString(),
    val action: String? = null,
    val message: String
)