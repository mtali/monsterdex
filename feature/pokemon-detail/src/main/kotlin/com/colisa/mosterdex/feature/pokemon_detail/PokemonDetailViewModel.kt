package com.colisa.mosterdex.feature.pokemon_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.colisa.monsterdex.core.data.repository.DetailRepository
import com.colisa.monsterdex.core.data.repository.MainRepository
import com.colisa.mosterdex.core.design_system.component.ToastMessage
import com.colisa.mosterdex.core.design_system.component.newToast
import com.colisa.mosterdex.feature.pokemon_detail.navigation.POKEMON_NAME
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
@OptIn(ExperimentalCoroutinesApi::class)
class PokemonDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val mainRepository: MainRepository,
    private val detailRepository: DetailRepository
) : ViewModel() {

    private val pokemonName =
        savedStateHandle.getStateFlow<String?>(key = POKEMON_NAME, initialValue = null)

    val message = MutableStateFlow<ToastMessage?>(null)
    val isLoading = MutableStateFlow(true)

    val pokemon = pokemonName
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

    val pokemonInfo = pokemonName
        .flatMapLatest { name ->
            if (name == null) {
                flowOf(null)
            } else {
                detailRepository.fetchPokemonInfo(
                    name = name,
                    onError = { error ->
                        isLoading.update { false }
                        error?.let { message.update { newToast(error) } }
                    },
                    onComplete = { isLoading.update { false } }
                )
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null,
        )

    fun clearMessage() = message.update { null }

}