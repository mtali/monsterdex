package com.colisa.monsterdex.core.data.repository

import androidx.paging.PagingData
import com.colisa.monsterdex.core.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun getPokemons(): Flow<PagingData<Pokemon>>
}