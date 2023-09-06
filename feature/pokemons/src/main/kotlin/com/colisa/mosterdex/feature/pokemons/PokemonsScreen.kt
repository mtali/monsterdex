package com.colisa.mosterdex.feature.pokemons

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
internal fun PokemonsRoute(viewModel: PokemonsViewModel = hiltViewModel()) {
    PokemonsScreen()
}

@Composable
internal fun PokemonsScreen() {
    Text(text = "Pokemons")
}

