package com.colisa.mosterdex.feature.pokemon_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.colisa.monsterdex.core.data.repository.MainRepository
import com.colisa.mosterdex.feature.pokemon_detail.navigation.POKEMON_NAME
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
@OptIn(ExperimentalCoroutinesApi::class)
class PokemonDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val mainRepository: MainRepository
) : ViewModel() {

    val pokemon = savedStateHandle.getStateFlow<String?>(key = POKEMON_NAME, initialValue = null)
        .flatMapLatest { name ->
            if (name == null) {
                flowOf(null)
            } else {
                mainRepository.getPokemon(name)
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null,
        )

}