package com.colisa.monsterdex.core.data.repository

import androidx.annotation.VisibleForTesting
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.map
import com.colisa.monsterdex.core.model.Pokemon
import com.colisa.monsterdex.core.network.Dispatcher
import com.colisa.monsterdex.core.network.MonsterdexAppDispatchers.IO
import com.colisa.mosterdex.core.database.PokemonDao
import com.colisa.mosterdex.core.database.entity.PokemonEntity
import com.colisa.mosterdex.core.database.entity.mapper.asDomain
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@VisibleForTesting
class MainRepositoryImpl @Inject constructor(
    private val pokemonPager: Pager<Int, PokemonEntity>,
    private val pokemonDao: PokemonDao,
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher
) : MainRepository {
    override fun getPokemons(): Flow<PagingData<Pokemon>> = pokemonPager.flow.map { pagingData ->
        pagingData.map { it.asDomain() }
    }

    override fun getPokemon(name: String): Flow<Pokemon?> =
        pokemonDao.getPokemon(name).map { it?.asDomain() }.flowOn(ioDispatcher)
}