package com.colisa.monsterdex.data.repository

import androidx.annotation.WorkerThread
import com.colisa.monsterdex.model.PokemonInfo
import kotlinx.coroutines.flow.Flow

interface DetailRepository {
    @WorkerThread
    fun fetchPokemonInfo(
        name: String,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<PokemonInfo>
}