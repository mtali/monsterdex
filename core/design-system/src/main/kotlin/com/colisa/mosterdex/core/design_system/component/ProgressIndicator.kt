package com.colisa.mosterdex.core.design_system.component

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun LinearProgressWithTextIndicator(
    modifier: Modifier = Modifier,
    progress: Float,
    text: String,
    progressColor: Color = ProgressIndicatorDefaults.linearColor,
    backgroundColor: Color = ProgressIndicatorDefaults.linearTrackColor,
    clipShape: Shape = RoundedCornerShape(16.dp)
) {
    val animatedProgress = remember { Animatable(0f) }
    LaunchedEffect(progress) {
        animatedProgress.animateTo(
            targetValue = progress,
            animationSpec = tween(1000)
        )
    }
    Box(
        modifier = modifier
            .clip(clipShape)
            .background(backgroundColor)
            .height(18.dp)
    ) {
        Box(
            modifier = Modifier
                .clip(clipShape)
                .background(progressColor)
                .fillMaxHeight()
                .fillMaxWidth(animatedProgress.value)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(animatedProgress.value)
                .animateContentSize(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                color = Color.White,
                modifier = Modifier.padding(horizontal = 6.dp),
                fontSize = 12.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
    }
}