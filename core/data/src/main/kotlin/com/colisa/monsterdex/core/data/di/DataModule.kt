package com.colisa.monsterdex.core.data.di

import com.colisa.monsterdex.core.data.repository.DetailRepository
import com.colisa.monsterdex.core.data.repository.DetailRepositoryImpl
import com.colisa.monsterdex.core.data.repository.MainRepository
import com.colisa.monsterdex.core.data.repository.MainRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
internal interface DataModule {

    @Binds
    fun bindMainRepository(mainRepository: MainRepositoryImpl): MainRepository

    @Binds
    fun bindDetailRepository(detailRepository: DetailRepositoryImpl): DetailRepository

}