package com.colisa.monsterdex.core.network.service

import com.colisa.monsterdex.core.model.PokemonInfo
import com.colisa.monsterdex.core.network.model.PokemonResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MosterdexService {
    @GET("pokemon")
    suspend fun fetchPokemons(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): ApiResponse<PokemonResponse>

    @GET("pokemon/{name}")
    suspend fun fetchPokemonInfo(@Path("name") name: String): ApiResponse<PokemonInfo>
}