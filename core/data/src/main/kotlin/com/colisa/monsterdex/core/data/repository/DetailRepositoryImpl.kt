package com.colisa.monsterdex.core.data.repository

import androidx.annotation.VisibleForTesting
import com.colisa.monsterdex.core.model.PokemonInfo
import com.colisa.monsterdex.core.network.Dispatcher
import com.colisa.monsterdex.core.network.MonsterdexAppDispatchers.IO
import com.colisa.monsterdex.core.network.model.mapper.ErrorResponseMapper
import com.colisa.monsterdex.core.network.service.MosterdexClient
import com.colisa.mosterdex.core.database.PokemonInfoDao
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
import javax.inject.Inject

@VisibleForTesting
class DetailRepositoryImpl @Inject constructor(
    private val mosterdexClient: MosterdexClient,
    private val pokemonInfoDao: PokemonInfoDao,
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher
) : DetailRepository {
    override fun fetchPokemonInfo(
        name: String,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<PokemonInfo> = flow {
        val pokemonInfo = pokemonInfoDao.getPokemonInfo(name)
        if (pokemonInfo == null) {
            val response = mosterdexClient.fetchPokemonInfo(name = name)
            response.suspendOnSuccess {
                pokemonInfoDao.insertPokemonInfo(data.asEntity())
                emit(data)
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
            emit(pokemonInfo.asDomain())
        }
    }.onCompletion { onComplete() }.flowOn(ioDispatcher)

}