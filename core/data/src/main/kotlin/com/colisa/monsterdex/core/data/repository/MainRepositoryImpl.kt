package com.colisa.monsterdex.core.data.repository

import androidx.annotation.VisibleForTesting
import androidx.annotation.WorkerThread
import com.colisa.monsterdex.core.model.Pokemon
import com.colisa.monsterdex.core.network.Dispatcher
import com.colisa.monsterdex.core.network.MonsterdexAppDispatchers.IO
import com.colisa.monsterdex.core.network.model.mapper.ErrorResponseMapper
import com.colisa.monsterdex.core.network.service.MosterdexClient
import com.colisa.mosterdex.core.database.PokemonDao
import com.colisa.mosterdex.core.database.entity.mapper.asDomain
import com.colisa.mosterdex.core.database.entity.mapper.asEntity
import com.skydoves.sandwich.map
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@VisibleForTesting
class MainRepositoryImpl @Inject constructor(
    private val mosterdexClient: MosterdexClient,
    private val pokemonDao: PokemonDao,
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher

) : MainRepository {

    @WorkerThread
    override fun fetchPokemons(
        page: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<List<Pokemon>> = flow {
        var pokemons = pokemonDao.getAllPokemons(page).asDomain()
        if (pokemons.isEmpty()) {
            val response = mosterdexClient.fetchPokemons(page = page)
            response.suspendOnSuccess {
                pokemons = data.results
                pokemons.forEach { pokemon -> pokemon.page = page }
                pokemonDao.insertPokemons(pokemons.asEntity())
                emit(pokemonDao.getPokemons(page = page).asDomain())
            }
                .onError {
                    map(ErrorResponseMapper) {
                        onError("[Code: $code]: $message")
                    }
                }
                .onFailure {
                    onError(message())
                }
        } else {
            emit(pokemonDao.getPokemons(page = page).asDomain())
        }
    }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(ioDispatcher)

}