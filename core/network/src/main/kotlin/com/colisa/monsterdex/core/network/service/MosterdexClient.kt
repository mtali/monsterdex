package com.colisa.monsterdex.core.network.service

import javax.inject.Inject

class MosterdexClient @Inject constructor(
    private val mosterdexService: MosterdexService
) {
    suspend fun fetchPokemons(page: Int) =
        mosterdexService.fetchPokemons(limit = PAGING_SIZE, offset = page * PAGING_SIZE)

    suspend fun fetchPokemonInfo(name: String) =
        mosterdexService.fetchPokemonInfo(name)

    companion object {
        private const val PAGING_SIZE = 20
    }
}