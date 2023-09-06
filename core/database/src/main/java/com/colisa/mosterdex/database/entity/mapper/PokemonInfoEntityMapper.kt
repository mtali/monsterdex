package com.colisa.mosterdex.database.entity.mapper

import com.colisa.monsterdex.model.PokemonInfo
import com.colisa.mosterdex.database.entity.PokemonInfoEntity

object PokemonInfoEntityMapper : EntityMapper<List<PokemonInfo>, List<PokemonInfoEntity>> {
    override fun asEntity(domain: List<PokemonInfo>): List<PokemonInfoEntity> {
        return domain.map { pokemonInfo ->
            PokemonInfoEntity(
                id = pokemonInfo.id,
                name = pokemonInfo.name,
                height = pokemonInfo.height,
                weight = pokemonInfo.weight,
                experience = pokemonInfo.experience,
                types = pokemonInfo.types,
                hp = pokemonInfo.hp,
                attack = pokemonInfo.attack,
                defense = pokemonInfo.defense,
                speed = pokemonInfo.speed,
                exp = pokemonInfo.exp
            )
        }
    }

    override fun asDomain(entity: List<PokemonInfoEntity>): List<PokemonInfo> {
        return entity.map { pokemonInfoEntity ->
            PokemonInfo(
                id = pokemonInfoEntity.id,
                name = pokemonInfoEntity.name,
                height = pokemonInfoEntity.height,
                weight = pokemonInfoEntity.weight,
                experience = pokemonInfoEntity.experience,
                types = pokemonInfoEntity.types,
                hp = pokemonInfoEntity.hp,
                attack = pokemonInfoEntity.attack,
                defense = pokemonInfoEntity.defense,
                speed = pokemonInfoEntity.speed,
                exp = pokemonInfoEntity.exp
            )
        }
    }
}

fun List<PokemonInfo>.asEntity() = PokemonInfoEntityMapper.asEntity(this)

fun List<PokemonInfoEntity>.asDomain() = PokemonInfoEntityMapper.asDomain(this)