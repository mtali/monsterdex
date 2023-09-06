package com.colisa.mosterdex.database.di

import android.content.Context
import androidx.room.Room
import com.colisa.mosterdex.database.MosterdexDatabase
import com.colisa.mosterdex.database.PokemonDao
import com.colisa.mosterdex.database.PokemonInfoDao
import com.colisa.mosterdex.database.TypeResponseConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {
    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
        typeResponseConverter: TypeResponseConverter
    ): MosterdexDatabase {
        return Room
            .databaseBuilder(context, MosterdexDatabase::class.java, "mosterdex.db")
            .fallbackToDestructiveMigration()
            .addTypeConverter(typeResponseConverter)
            .build()
    }

    @Provides
    @Singleton
    fun provideTypeResponseConverter(moshi: Moshi): TypeResponseConverter {
        return TypeResponseConverter(moshi)
    }

    @Provides
    @Singleton
    fun providePokemonDao(database: MosterdexDatabase): PokemonDao {
        return database.pokemonDao()
    }

    @Provides
    @Singleton
    fun providePokemonInfoDao(database: MosterdexDatabase): PokemonInfoDao {
        return database.pokemonInfoDao()
    }
}