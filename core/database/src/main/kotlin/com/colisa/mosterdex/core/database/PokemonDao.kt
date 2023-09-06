package com.colisa.mosterdex.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.colisa.mosterdex.core.database.entity.PokemonEntity

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemons(pokemons: List<PokemonEntity>)

    @Query("SELECT * FROM pokemons WHERE page = :page")
    fun getPokemons(page: Int): List<PokemonEntity>

    @Query("SELECT * FROM pokemons WHERE page <= :page")
    fun getAllPokemons(page: Int): List<PokemonEntity>
}