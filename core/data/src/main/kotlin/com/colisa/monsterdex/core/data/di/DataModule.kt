package com.colisa.monsterdex.core.data.di

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.colisa.monsterdex.core.data.PokemonRemoteMediator
import com.colisa.monsterdex.core.data.repository.DetailRepository
import com.colisa.monsterdex.core.data.repository.DetailRepositoryImpl
import com.colisa.monsterdex.core.data.repository.MainRepository
import com.colisa.monsterdex.core.data.repository.MainRepositoryImpl
import com.colisa.monsterdex.core.network.service.MosterdexClient
import com.colisa.mosterdex.core.database.PokemonDao
import com.colisa.mosterdex.core.database.RemoteKeyDao
import com.colisa.mosterdex.core.database.entity.PokemonEntity
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindMainRepository(mainRepository: MainRepositoryImpl): MainRepository

    @Binds
    fun bindDetailRepository(detailRepository: DetailRepositoryImpl): DetailRepository

    companion object {
        @OptIn(ExperimentalPagingApi::class)
        @Provides
        @Singleton
        fun providePokemonPager(
            pokemonDao: PokemonDao,
            remoteKeyDao: RemoteKeyDao,
            mosterdexClient: MosterdexClient
        ): Pager<Int, PokemonEntity> {
            return Pager(
                config = PagingConfig(pageSize = 20),
                remoteMediator = PokemonRemoteMediator(
                    pokemonDao, remoteKeyDao, mosterdexClient
                ),
                pagingSourceFactory = {
                    pokemonDao.getAllPokemons()
                }
            )
        }
    }
}