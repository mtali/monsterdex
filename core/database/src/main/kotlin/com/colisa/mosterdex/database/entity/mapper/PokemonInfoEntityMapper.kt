package com.colisa.mosterdex.database.entity.mapper

import com.colisa.monsterdex.model.PokemonInfo
import com.colisa.mosterdex.database.entity.PokemonInfoEntity

object PokemonInfoEntityMapper : EntityMapper<PokemonInfo, PokemonInfoEntity> {
    override fun asEntity(domain: PokemonInfo): PokemonInfoEntity {
        return PokemonInfoEntity(
            id = domain.id,
            name = domain.name,
            height = domain.height,
            weight = domain.weight,
            experience = domain.experience,
            types = domain.types,
            hp = domain.hp,
            attack = domain.attack,
            defense = domain.defense,
            speed = domain.speed,
            exp = domain.exp
        )
    }

    override fun asDomain(entity: PokemonInfoEntity): PokemonInfo {
        return PokemonInfo(
            id = entity.id,
            name = entity.name,
            height = entity.height,
            weight = entity.weight,
            experience = entity.experience,
            types = entity.types,
            hp = entity.hp,
            attack = entity.attack,
            defense = entity.defense,
            speed = entity.speed,
            exp = entity.exp
        )
    }

}


fun PokemonInfo.asEntity() = PokemonInfoEntityMapper.asEntity(this)

fun PokemonInfoEntity.asDomain() = PokemonInfoEntityMapper.asDomain(this)