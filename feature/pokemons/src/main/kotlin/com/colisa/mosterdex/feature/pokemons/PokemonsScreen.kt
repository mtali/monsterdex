package com.colisa.mosterdex.feature.pokemons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
internal fun PokemonsRoute(viewModel: PokemonsViewModel = hiltViewModel()) {
    PokemonsScreen()
}

@Composable
internal fun PokemonsScreen() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier,
        contentPadding = PaddingValues(6.dp),
    ) {
        items(100) { index ->
            PokemonItemCard(pokemon = "Pokemon #${index}")
        }
    }
}

@Composable
fun PokemonItemCard(
    modifier: Modifier = Modifier,
    pokemon: String,
    onPokemonClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onPokemonClick() },
        elevation = CardDefaults.elevatedCardElevation(),
        shape = MaterialTheme.shapes.medium,
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(16.dp),
                contentAlignment = Alignment.BottomStart,
            ) {
                Text(
                    text = pokemon,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.alpha(0.7f)
                )
            }
        }
    }
}



