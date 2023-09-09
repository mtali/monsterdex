package com.colisa.mosterdex.feature.pokemon_detail.navigation

import android.net.Uri
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.colisa.mosterdex.feature.pokemon_detail.PokemonDetailRoute

const val POKEMON_NAME = "name"
const val pokemonDetailRoute = "pokemon_detail_route/{$POKEMON_NAME}"

fun NavController.navigateToPokemonDetail(name: String, navOptions: NavOptions? = null) {
    this.navigate("pokemon_detail_route/${Uri.encode(name)}", navOptions)
}

fun NavGraphBuilder.pokemonDetailScreen(onBackClick: () -> Unit) {
    composable(
        route = pokemonDetailRoute,
        arguments = listOf(
            navArgument(POKEMON_NAME) { type = NavType.StringType }
        )
    ) {
        PokemonDetailRoute(onBackClick = onBackClick)
    }
}