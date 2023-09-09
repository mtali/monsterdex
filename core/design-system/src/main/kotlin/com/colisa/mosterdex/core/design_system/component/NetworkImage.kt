package com.colisa.mosterdex.core.design_system.component

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.colisa.mosterdex.core.design_system.getDominantColor
import kotlinx.coroutines.launch

@Composable
fun NetworkImage(
    url: String,
    dominantColor: (Color) -> Unit,
    modifier: Modifier = Modifier
) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .allowHardware(false) // Palette needs to read the image's pixels
            .size(Size.ORIGINAL) // Set the target size to load the image at.
            .build()
    )

    when (val state = painter.state) {
        is AsyncImagePainter.State.Success -> {
            LaunchedEffect(Unit) {
                launch {
                    getDominantColor(state.result.drawable) { dominantColor(it) }
                }
            }
        }

        else -> Unit
    }
    Image(
        painter = painter,
        contentDescription = null,
        modifier = modifier
    )
}