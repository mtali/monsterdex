package com.colisa.monsterdex.core.data.repository

import androidx.annotation.WorkerThread
import com.colisa.monsterdex.core.model.PokemonInfo
import kotlinx.coroutines.flow.Flow

interface DetailRepository {
    @WorkerThread
    fun fetchPokemonInfo(
        name: String,
        onComplete: () -> Unit = {},
        onError: (String?) -> Unit = {}
    ): Flow<PokemonInfo>
}