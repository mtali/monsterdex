package com.colisa.mosterdex.database.entity.mapper

import com.colisa.monsterdex.model.Pokemon
import com.colisa.mosterdex.database.entity.PokemonEntity

object PokemonEntityMapper : EntityMapper<List<Pokemon>, List<PokemonEntity>> {
    override fun asEntity(domain: List<Pokemon>): List<PokemonEntity> {
        return domain.map { pokemon ->
            PokemonEntity(
                page = pokemon.page,
                name = pokemon.name,
                url = pokemon.url
            )
        }
    }

    override fun asDomain(entity: List<PokemonEntity>): List<Pokemon> {
        return entity.map { pokemonEntity ->
            Pokemon(
                page = pokemonEntity.page,
                name = pokemonEntity.name,
                url = pokemonEntity.url
            )
        }
    }
}

fun List<Pokemon>.asEntity() = PokemonEntityMapper.asEntity(this)

fun List<PokemonEntity>.asDomain() = PokemonEntityMapper.asDomain(this)