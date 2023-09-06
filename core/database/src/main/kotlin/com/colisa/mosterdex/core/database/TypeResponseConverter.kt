package com.colisa.mosterdex.core.database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.colisa.monsterdex.core.model.PokemonInfo
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import javax.inject.Inject

@ProvidedTypeConverter
class TypeResponseConverter @Inject constructor(
    private val moshi: Moshi
) {
    @TypeConverter
    fun fromString(value: String): List<PokemonInfo.TypeResponse>? {
        return getJsonAdapter().fromJson(value)
    }

    @TypeConverter
    fun fromInfoType(type: List<PokemonInfo.TypeResponse>?): String {
        return getJsonAdapter().toJson(type)
    }

    private fun getJsonAdapter(): JsonAdapter<List<PokemonInfo.TypeResponse>> {
        val listType =
            Types.newParameterizedType(List::class.java, PokemonInfo.TypeResponse::class.java)
        return moshi.adapter(listType)
    }
}