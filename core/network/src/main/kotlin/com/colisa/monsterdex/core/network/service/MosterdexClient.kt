package com.colisa.monsterdex.core.network.service

import javax.inject.Inject

class MosterdexClient @Inject constructor(
    private val mosterdexService: MosterdexService
) {
    suspend fun fetchPokemons(limit: Int, offset: Int) =
        mosterdexService.fetchPokemons(limit = limit, offset = offset)

    suspend fun fetchPokemonInfo(name: String) =
        mosterdexService.fetchPokemonInfo(name)

}