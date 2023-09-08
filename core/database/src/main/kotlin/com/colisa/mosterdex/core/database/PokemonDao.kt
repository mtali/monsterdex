package com.colisa.mosterdex.core.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.colisa.mosterdex.core.database.entity.PokemonEntity

@Dao
interface PokemonDao {
    @Upsert
    suspend fun insertPokemons(pokemons: List<PokemonEntity>)

    @Query("SELECT * FROM pokemons")
    fun getAllPokemons(): PagingSource<Int, PokemonEntity>

    @Query("DELETE FROM pokemons")
    suspend fun clearAllPokemons()
}