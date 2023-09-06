package com.colisa.monsterdex.data.repository

import androidx.annotation.WorkerThread
import com.colisa.monsterdex.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    @WorkerThread
    fun fetchPokemons(
        page: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<List<Pokemon>>

}