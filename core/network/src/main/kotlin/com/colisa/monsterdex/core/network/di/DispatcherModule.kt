package com.colisa.monsterdex.core.network.di

import com.colisa.monsterdex.core.network.Dispatcher
import com.colisa.monsterdex.core.network.MonsterdexAppDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
internal object DispatcherModule {
    @Provides
    @Dispatcher(MonsterdexAppDispatchers.IO)
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO
}