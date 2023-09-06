package com.colisa.mosterdex.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemons")
data class PokemonEntity(
    @ColumnInfo(name = "page") var page: Int = 0,
    @PrimaryKey @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "url") val url: String
)