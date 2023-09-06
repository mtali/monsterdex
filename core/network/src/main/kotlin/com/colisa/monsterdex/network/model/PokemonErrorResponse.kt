package com.colisa.monsterdex.network.model

/**
 * A customized pokemon error message
 *
 * @param code A network response code
 * @param message A network error message
 */
data class PokemonErrorResponse(
    val code: Int,
    val message: String?
)