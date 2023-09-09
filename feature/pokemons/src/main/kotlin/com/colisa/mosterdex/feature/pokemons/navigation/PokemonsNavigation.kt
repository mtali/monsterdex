package com.colisa.mosterdex.feature.pokemons.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.colisa.mosterdex.feature.pokemons.PokemonsRoute

const val pokemonsRoute = "pokemons_route"

fun NavGraphBuilder.pokemonsScreen(onPokemonClick: (String) -> Unit) {
    composable(route = pokemonsRoute) {
        PokemonsRoute(onPokemonClick = onPokemonClick)
    }
}