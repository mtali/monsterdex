package com.colisa.mosterdex.core.design_system.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


fun LazyListScope.spacer(height: Dp = 0.dp, width: Dp = 0.dp) {
    item {
        Spacer(
            modifier = Modifier
                .height(height)
                .width(width)
        )
    }
}