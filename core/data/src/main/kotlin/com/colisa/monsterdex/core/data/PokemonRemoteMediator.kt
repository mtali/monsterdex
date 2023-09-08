package com.colisa.monsterdex.core.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.colisa.monsterdex.core.network.parseOffsetFromUrl
import com.colisa.monsterdex.core.network.service.MosterdexClient
import com.colisa.mosterdex.core.database.PokemonDao
import com.colisa.mosterdex.core.database.RemoteKeyDao
import com.colisa.mosterdex.core.database.entity.PokemonEntity
import com.colisa.mosterdex.core.database.entity.RemoteKeyEntity
import com.colisa.mosterdex.core.database.entity.mapper.asEntity
import com.skydoves.sandwich.getOrThrow

private const val REMOTE_KEY_ID = "pokemon"

@OptIn(ExperimentalPagingApi::class)
class PokemonRemoteMediator(
    private val pokemonDao: PokemonDao,
    private val remoteKeyDao: RemoteKeyDao,
    private val mosterdexClient: MosterdexClient,
) : RemoteMediator<Int, PokemonEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonEntity>
    ): MediatorResult {
        return try {

            val offset = when (loadType) {
                LoadType.REFRESH -> 0
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    // Retrieve next offset from database
                    val remoteKey = remoteKeyDao.getRemoteKey(REMOTE_KEY_ID)
                    if (remoteKey == null || remoteKey.nextOffset == 0)
                        return MediatorResult.Success(endOfPaginationReached = true)
                    remoteKey.nextOffset
                }
            }

            // Make API call
            val response =
                mosterdexClient.fetchPokemons(offset = offset, limit = state.config.pageSize)
                    .getOrThrow()
            val pokemons = response.results
            val nextOffset = parseOffsetFromUrl(response.next) ?: 0

            // Safe results and next offset
            // TODO: Move all db operation to a transaction
            if (loadType == LoadType.REFRESH) {
                pokemonDao.clearAllPokemons()
                remoteKeyDao.deleteRemoteKey(REMOTE_KEY_ID)
            }

            pokemonDao.insertPokemons(pokemons.map { it.asEntity() })
            remoteKeyDao.insertRemoteKey(RemoteKeyEntity(REMOTE_KEY_ID, nextOffset))

            MediatorResult.Success(endOfPaginationReached = pokemons.size < state.config.pageSize)

        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}
