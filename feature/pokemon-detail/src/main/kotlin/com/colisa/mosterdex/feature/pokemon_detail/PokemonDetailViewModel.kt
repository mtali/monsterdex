package com.colisa.mosterdex.feature.pokemon_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.colisa.mosterdex.feature.pokemon_detail.navigation.PokemonsDetailArg
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val args = PokemonsDetailArg(savedStateHandle)

}