package com.colisa.monsterdex.core.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Pokemon(
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "url")
    val url: String
) : Parcelable {
    fun getImageUrl(): String {
        val index = getIndex()
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$index.png"
    }

    fun getIndex() = url.split("/").dropLast(1).last()
}
