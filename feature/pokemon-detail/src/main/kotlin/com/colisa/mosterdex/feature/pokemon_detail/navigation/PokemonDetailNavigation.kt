package com.colisa.mosterdex.feature.pokemon_detail.navigation

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.colisa.mosterdex.feature.pokemon_detail.PokemonDetailRoute

private const val pokemonNameArg = "name"
const val pokemonDetailRoute = "pokemon_detail_route/{$pokemonNameArg}"

class PokemonsDetailArg(val name: String) {
    constructor(savedStateHandle: SavedStateHandle) : this(
        checkNotNull(Uri.decode(savedStateHandle.get<String>(pokemonNameArg)))
    )
}

fun NavController.navigateToPokemonDetail(name: String, navOptions: NavOptions? = null) {
    this.navigate("pokemon_detail_route/${Uri.encode(name)}", navOptions)
}

fun NavGraphBuilder.pokemonDetailScreen() {
    composable(
        route = pokemonDetailRoute,
        arguments = listOf(
            navArgument(pokemonNameArg) { type = NavType.StringType }
        )
    ) {
        PokemonDetailRoute()
    }
}