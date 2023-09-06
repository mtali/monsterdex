package com.colisa.monsterdex.network.di

import com.colisa.monsterdex.network.interceptor.HttpRequestInterceptor
import com.colisa.monsterdex.network.service.MosterdexClient
import com.colisa.monsterdex.network.service.MosterdexService
import com.skydoves.sandwich.adapters.ApiResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    private const val BASE_URL = "https://pokeapi.co/api/v2/"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpRequestInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideMosterdexService(retrofit: Retrofit): MosterdexService {
        return retrofit.create(MosterdexService::class.java)
    }

    @Provides
    @Singleton
    fun provideMosterdexClient(service: MosterdexService): MosterdexClient {
        return MosterdexClient(service)
    }
}