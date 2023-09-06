package com.colisa.mosterdex.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.colisa.mosterdex.core.database.entity.PokemonEntity
import com.colisa.mosterdex.core.database.entity.PokemonInfoEntity

@Database(
    entities = [PokemonEntity::class, PokemonInfoEntity::class],
    exportSchema = true,
    version = 1
)
@TypeConverters(value = [TypeResponseConverter::class])
abstract class MosterdexDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao

    abstract fun pokemonInfoDao(): PokemonInfoDao
}