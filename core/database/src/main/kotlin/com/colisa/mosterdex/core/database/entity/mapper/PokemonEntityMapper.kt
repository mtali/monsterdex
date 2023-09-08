package com.colisa.mosterdex.core.database.entity.mapper

import com.colisa.monsterdex.core.model.Pokemon
import com.colisa.mosterdex.core.database.entity.PokemonEntity

object PokemonEntityMapper : EntityMapper<Pokemon, PokemonEntity> {

    override fun asEntity(domain: Pokemon): PokemonEntity {
        return PokemonEntity(
            name = domain.name,
            url = domain.url
        )
    }

    override fun asDomain(entity: PokemonEntity): Pokemon {
        return Pokemon(
            name = entity.name,
            url = entity.url
        )
    }
}

fun Pokemon.asEntity() = PokemonEntityMapper.asEntity(this)

fun PokemonEntity.asDomain() = PokemonEntityMapper.asDomain(this)