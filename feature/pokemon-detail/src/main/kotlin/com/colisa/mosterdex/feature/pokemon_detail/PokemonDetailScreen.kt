package com.colisa.mosterdex.feature.pokemon_detail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
internal fun PokemonDetailRoute(viewModel: PokemonDetailViewModel = hiltViewModel()) {
    PokemonDetailScreen()
}

@Composable
internal fun PokemonDetailScreen() {
    Text(text = "Pokemon Detail")
}

