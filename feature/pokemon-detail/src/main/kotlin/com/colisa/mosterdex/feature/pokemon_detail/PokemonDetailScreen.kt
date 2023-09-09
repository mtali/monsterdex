package com.colisa.mosterdex.feature.pokemon_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.colisa.mosterdex.core.design_system.component.NetworkImage
import com.colisa.mosterdex.core.design_system.icon.MonsterdexIcons

@Composable
internal fun PokemonDetailRoute(viewModel: PokemonDetailViewModel = hiltViewModel()) {
    PokemonDetailScreen(onBackClick = {})
}

@Composable
internal fun PokemonDetailScreen(
    onBackClick: () -> Unit
) {
    val colorScheme = MaterialTheme.colorScheme
    var headerBackground by remember { mutableStateOf(colorScheme.primary) }

    Column(modifier = Modifier.fillMaxSize()) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp),
            shape = RoundedCornerShape(bottomEnd = 42.dp, bottomStart = 42.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(headerBackground)
            ) {
                Toolbar(onBackClick = onBackClick, index = 12)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    NetworkImage(
                        url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png",
                        dominantColor = { headerBackground = it },
                        modifier = Modifier.size(190.dp)
                    )
                }
            }
        }
    }
}


@Composable
private fun Toolbar(
    modifier: Modifier = Modifier,
    index: Int,
    onBackClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onBackClick) {
            Icon(
                imageVector = MonsterdexIcons.ArrowBack,
                contentDescription = "back",
                tint = Color.White
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "Mosterdex",
            style = MaterialTheme.typography.titleMedium,
            color = Color.White
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "#$index",
            style = MaterialTheme.typography.titleMedium,
            color = Color.White
        )
        Spacer(
            modifier = Modifier.width(16.dp)
        )
    }
}

