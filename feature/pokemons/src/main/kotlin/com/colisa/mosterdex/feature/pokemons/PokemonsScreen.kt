package com.colisa.mosterdex.feature.pokemons

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import androidx.palette.graphics.Palette
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.colisa.monsterdex.core.model.Pokemon
import kotlinx.coroutines.launch


@Composable
internal fun PokemonsRoute(
    viewModel: PokemonsViewModel = hiltViewModel(),
    onPokemonClick: (name: String) -> Unit
) {
    val pokemonsPagingItems = viewModel.pokemons.collectAsLazyPagingItems()
    PokemonsScreen(
        pokemonsPagingItems = pokemonsPagingItems,
        onPokemonClick = onPokemonClick
    )
}

@Composable
internal fun PokemonsScreen(
    pokemonsPagingItems: LazyPagingItems<Pokemon>,
    onPokemonClick: (name: String) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        val loading = pokemonsPagingItems.loadState.refresh is LoadState.Loading ||
                pokemonsPagingItems.loadState.append is LoadState.Loading

        if (loading) {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier,
            contentPadding = PaddingValues(6.dp),
        ) {
            items(
                count = pokemonsPagingItems.itemCount,
                key = pokemonsPagingItems.itemKey { it.name }
            ) { index ->
                val pokemon = pokemonsPagingItems[index]
                if (pokemon != null) {
                    PokemonItemCard(
                        pokemon = pokemon,
                        onPokemonClick = onPokemonClick
                    )
                }
            }
        }
    }
}

@Composable
private fun PokemonItemCard(
    modifier: Modifier = Modifier,
    pokemon: Pokemon,
    onPokemonClick: (name: String) -> Unit
) {

    val colorScheme = MaterialTheme.colorScheme

    var backgroundColor by remember { mutableStateOf(colorScheme.primary) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onPokemonClick(pokemon.name) },
        elevation = CardDefaults.elevatedCardElevation(),
        shape = MaterialTheme.shapes.medium,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(MaterialTheme.shapes.medium)
                .background(backgroundColor)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val url = pokemon.getImageUrl()
            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(url)
                    .crossfade(true)
                    .allowHardware(false) // Palette needs to read the image's pixels
                    .size(Size.ORIGINAL) // Set the target size to load the image at.
                    .build()
            )

            when (val state = painter.state) {
                is AsyncImagePainter.State.Success -> {
                    LaunchedEffect(Unit) {
                        launch {
                            getDominantColor(state.result.drawable) { color ->
                                backgroundColor = color.copy(alpha = 0.8f)
                            }
                        }
                    }
                }

                else -> Unit
            }

            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .height(120.dp)
                    .width(120.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = pokemon.name,
                style = MaterialTheme.typography.titleMedium,
                color = Color.White,
                modifier = Modifier.alpha(0.7f)
            )
        }
    }
}

private fun getDominantColor(drawable: Drawable, onDominant: (Color) -> Unit) {
    Palette.Builder(drawable.toBitmap()).generate { palette ->
        palette?.let {
            palette.dominantSwatch?.rgb?.let { onDominant(Color(it)) }
        }
    }
}



