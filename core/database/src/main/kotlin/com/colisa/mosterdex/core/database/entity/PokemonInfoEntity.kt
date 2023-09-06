package com.colisa.mosterdex.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.colisa.monsterdex.core.model.PokemonInfo

@Entity(tableName = "pokemon_info")
data class PokemonInfoEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "height") val height: Int,
    @ColumnInfo(name = "weight") val weight: Int,
    @ColumnInfo(name = "experience") val experience: Int,
    @ColumnInfo(name = "types") val types: List<PokemonInfo.TypeResponse>,
    @ColumnInfo(name = "hp") val hp: Int,
    @ColumnInfo(name = "attack") val attack: Int,
    @ColumnInfo(name = "defense") val defense: Int,
    @ColumnInfo(name = "speed") val speed: Int,
    @ColumnInfo(name = "exp") val exp: Int
)
