package com.colisa.mosterdex.feature.pokemons.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.colisa.mosterdex.feature.pokemons.PokemonsRoute

const val pokemonsRoute = "pokemons_route"

fun NavController.navigateToPokemons(navOptions: NavOptions? = null) {
    this.navigate(pokemonsRoute, navOptions)
}

fun NavGraphBuilder.pokemonsScreen() {
    composable(route = pokemonsRoute) {
        PokemonsRoute()
    }
}