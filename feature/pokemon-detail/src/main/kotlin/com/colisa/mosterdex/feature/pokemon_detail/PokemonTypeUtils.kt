package com.colisa.mosterdex.feature.pokemon_detail

import androidx.compose.ui.graphics.Color

object PokemonTypeUtils {
    fun getTypeColor(type: String): Color {
        return when (type) {
            "fighting" -> Color(0xFF9F422A)
            "flying" -> Color(0xFF90B1C5)
            "poison" -> Color(0xFF642785)
            "ground" -> Color(0xFFAD7235)
            "rock" -> Color(0xFF4B190E)
            "bug" -> Color(0xFF179A55)
            "ghost" -> Color(0xFF363069)
            "steel" -> Color(0xFF5C756D)
            "fire" -> Color(0xFFB22328)
            "water" -> Color(0xFF2648DC)
            "grass" -> Color(0xFF007C42)
            "electric" -> Color(0xFFE0E64B)
            "psychic" -> Color(0xFFAC296B)
            "ice" -> Color(0xFF7ECFF2)
            "dragon" -> Color(0xFF378A94)
            "fairy" -> Color(0xFF9E1A44)
            "dark" -> Color(0xFF040706)
            else -> Color(0xFFB1A5A5)
        }
    }
}