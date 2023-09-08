package com.colisa.mosterdex.feature.pokemons

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.colisa.monsterdex.core.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonsViewModel @Inject constructor(
    mainRepository: MainRepository
) : ViewModel() {

    val pokemons = mainRepository.getPokemons().cachedIn(viewModelScope)
}