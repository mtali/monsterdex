package com.colisa.mosterdex.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.colisa.mosterdex.core.database.entity.PokemonInfoEntity

@Dao
interface PokemonInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonInfo(pokemonInfoDao: PokemonInfoEntity)

    @Query("SELECT * FROM pokemon_info WHERE name = :name")
    suspend fun getPokemonInfo(name: String): PokemonInfoEntity?
}