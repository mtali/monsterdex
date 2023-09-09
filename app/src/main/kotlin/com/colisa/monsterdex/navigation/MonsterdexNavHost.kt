/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.colisa.monsterdex.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.colisa.mosterdex.feature.pokemon_detail.navigation.navigateToPokemonDetail
import com.colisa.mosterdex.feature.pokemon_detail.navigation.pokemonDetailScreen
import com.colisa.mosterdex.feature.pokemons.navigation.pokemonsRoute
import com.colisa.mosterdex.feature.pokemons.navigation.pokemonsScreen


@Composable
fun MonsterdexNavHost(
    modifier: Modifier = Modifier,
    startDestination: String = pokemonsRoute
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {

        pokemonsScreen(onPokemonClick = { name ->
            navController.navigateToPokemonDetail(name)
        })

        pokemonDetailScreen()
    }
}
