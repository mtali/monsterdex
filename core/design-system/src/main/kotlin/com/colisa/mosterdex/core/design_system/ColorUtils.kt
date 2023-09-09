package com.colisa.mosterdex.core.design_system

import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.Color
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette

internal fun getDominantColor(drawable: Drawable, onDominant: (Color) -> Unit) {
    Palette.Builder(drawable.toBitmap()).generate { palette ->
        palette?.let {
            palette.dominantSwatch?.rgb?.let { onDominant(Color(it)) }
        }
    }
}