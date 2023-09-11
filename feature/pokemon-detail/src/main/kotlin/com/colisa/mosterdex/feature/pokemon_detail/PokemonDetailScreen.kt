package com.colisa.mosterdex.feature.pokemon_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.colisa.monsterdex.core.model.Pokemon
import com.colisa.monsterdex.core.model.PokemonInfo
import com.colisa.mosterdex.core.design_system.component.LinearProgressWithTextIndicator
import com.colisa.mosterdex.core.design_system.component.NetworkImage
import com.colisa.mosterdex.core.design_system.component.spacer
import com.colisa.mosterdex.core.design_system.icon.MonsterdexIcons
import com.colisa.mosterdex.feature.pokemon_detail.PokemonTypeUtils.getTypeColor

@Composable
internal fun PokemonDetailRoute(
    viewModel: PokemonDetailViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {

    val pokemon by viewModel.pokemon.collectAsStateWithLifecycle()
    val pokemonInfo by viewModel.pokemonInfo.collectAsStateWithLifecycle()
    PokemonDetailScreen(
        pokemon = pokemon,
        pokemonInfo = pokemonInfo,
        onBackClick = onBackClick
    )
}

@Composable
internal fun PokemonDetailScreen(
    pokemon: Pokemon?,
    pokemonInfo: PokemonInfo?,
    onBackClick: () -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        header(pokemon = pokemon, onBackClick = onBackClick)
        spacer(height = 8.dp)
        pokemonName(name = pokemon?.name)

        pokemonInfo?.let {
            pokemonType(types = pokemonInfo.types.map { it.type.name })
            spacer(height = 10.dp)
            pokemonInfo(
                info = pokemonInfo
            )
        }

    }
}

private fun LazyListScope.pokemonType(types: List<String>) {
    item {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            types.forEach { type ->
                AssistChip(
                    onClick = { },
                    label = { Text(text = type, modifier = Modifier.padding(horizontal = 16.dp)) },
                    border = null,
                    shape = RoundedCornerShape(percent = 50),
                    colors = AssistChipDefaults.assistChipColors(
                        containerColor = getTypeColor(type),
                        labelColor = Color.White
                    )
                )
            }
        }
    }
}

@Composable
private fun TitleValue(value: String, title: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = value, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text(text = title)
    }
}

@Composable
private fun WeightHeight(weight: String, height: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        TitleValue(value = weight, title = "Weight")
        TitleValue(value = height, title = "Height")
    }
}

private fun LazyListScope.pokemonInfo(info: PokemonInfo) {
    item {
        WeightHeight(weight = info.getWeightString(), height = info.getHeightString())
        Spacer(modifier = Modifier.height(10.dp))
        BaseStats(info = info)
    }
}

@Composable
fun BaseStats(info: PokemonInfo) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(
            text = "Base Stats",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(6.dp))
        ProgressStats(
            title = "HP",
            current = info.hp,
            max = PokemonInfo.maxHp,
            text = info.getHpString(),
            progressColor = Color(0xFFD53A47)
        )
        Spacer(modifier = Modifier.height(8.dp))
        ProgressStats(
            title = "ATK",
            current = info.attack,
            max = PokemonInfo.maxAttack,
            text = info.getAttackString(),
            progressColor = Color(0xFFFFA726)
        )
        Spacer(modifier = Modifier.height(8.dp))
        ProgressStats(
            title = "DEF",
            current = info.defense,
            max = PokemonInfo.maxDefense,
            text = info.getDefenseString(),
            progressColor = Color(0xFF0091EA)
        )
        Spacer(modifier = Modifier.height(8.dp))
        ProgressStats(
            title = "SPD",
            current = info.speed,
            max = PokemonInfo.maxSpeed,
            text = info.getSpeedString(),
            progressColor = Color(0xFF90B1C5)
        )
        Spacer(modifier = Modifier.height(8.dp))
        ProgressStats(
            title = "EXP",
            current = info.exp,
            max = PokemonInfo.maxExp,
            text = info.getExpString(),
            progressColor = Color(0xFF388E3C)
        )
    }
}

@Composable
private fun ProgressStats(
    modifier: Modifier = Modifier,
    title: String,
    current: Int,
    max: Int,
    text: String,
    progressColor: Color
) {
    Row(modifier.fillMaxWidth()) {
        Row(Modifier.weight(1f)) {
            Text(text = title)
        }
        LinearProgressWithTextIndicator(
            modifier = Modifier.weight(7f),
            progress = current.toFloat() / max.toFloat(),
            text = text,
            progressColor = progressColor
        )
    }
}


private fun LazyListScope.pokemonName(name: String?) {
    item {
        name?.let {
            Text(
                text = name,
                fontSize = 36.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}


private fun LazyListScope.header(pokemon: Pokemon?, onBackClick: () -> Unit) {
    item {
        val colorScheme = MaterialTheme.colorScheme
        var headerBackground by remember { mutableStateOf(colorScheme.primary) }
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
                Toolbar(onBackClick = onBackClick, index = pokemon?.getIndex())
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    if (pokemon != null) {
                        NetworkImage(
                            url = pokemon.getImageUrl(),
                            dominantColor = { headerBackground = it },
                            modifier = Modifier.size(190.dp)
                        )
                    }
                }
            }
        }
    }
}


@Composable
private fun Toolbar(
    modifier: Modifier = Modifier,
    index: String?,
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
            text = "#${index ?: 0}",
            style = MaterialTheme.typography.titleMedium,
            color = Color.White,
            fontSize = 18.sp
        )
        Spacer(
            modifier = Modifier.width(16.dp)
        )
    }
}

