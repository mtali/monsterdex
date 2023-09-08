package com.colisa.mosterdex.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.colisa.mosterdex.core.database.entity.PokemonEntity
import com.colisa.mosterdex.core.database.entity.PokemonInfoEntity
import com.colisa.mosterdex.core.database.entity.RemoteKeyEntity

@Database(
    entities = [PokemonEntity::class, PokemonInfoEntity::class, RemoteKeyEntity::class],
    exportSchema = true,
    version = 2
)
@TypeConverters(value = [TypeResponseConverter::class])
abstract class MosterdexDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao

    abstract fun pokemonInfoDao(): PokemonInfoDao

    abstract fun remoteKeyDao(): RemoteKeyDao
}