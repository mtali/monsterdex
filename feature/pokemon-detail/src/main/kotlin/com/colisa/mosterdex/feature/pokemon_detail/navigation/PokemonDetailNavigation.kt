package com.colisa.mosterdex.feature.pokemon_detail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.colisa.mosterdex.feature.pokemon_detail.PokemonDetailRoute

const val pokemonDetailRoute = "pokemon_detail_route"

fun NavController.navigateToPokemonDetail(navOptions: NavOptions? = null) {
    this.navigate(pokemonDetailRoute, navOptions)
}

fun NavGraphBuilder.pokemonsScreen() {
    composable(route = pokemonDetailRoute) {
        PokemonDetailRoute()
    }
}