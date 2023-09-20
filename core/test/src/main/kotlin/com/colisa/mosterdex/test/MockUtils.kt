package com.colisa.mosterdex.test

import com.colisa.monsterdex.core.model.Pokemon
import com.colisa.monsterdex.core.model.PokemonInfo

object MockUtils {

    fun mockPokemon() = Pokemon(
        name = "bulbasaur",
        url = "https://pokeapi.co/api/v2/pokemon/1/"
    )


    fun mockPokemonList() = listOf(mockPokemon())

    fun mockPokemonInfo() = PokemonInfo(
        id = 1,
        name = "bulbasaur",
        height = 7,
        weight = 69,
        experience = 60,
        types = emptyList()
    )

}