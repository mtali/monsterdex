package com.colisa.monsterdex.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.colisa.monsterdex.R
import com.colisa.monsterdex.navigation.MonsterdexNavHost
import com.colisa.mosterdex.core.design_system.component.MonsterdexBackground
import com.colisa.mosterdex.core.design_system.component.MonsterdexGradientBackground
import com.colisa.mosterdex.core.design_system.theme.GradientColors

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MonsterdexApp(
    appState: MosterdexAppState
) {
    MonsterdexBackground {
        MonsterdexGradientBackground(
            gradientColors = GradientColors()
        ) {

            val snackbarHostState = remember { SnackbarHostState() }

            val isOffline by appState.isOffline.collectAsStateWithLifecycle()

            val notConnectedMessage = stringResource(R.string.not_connected)

            LaunchedEffect(isOffline) {
                if (isOffline) {
//                    snackbarHostState.showSnackbar(
//                        message = notConnectedMessage,
//                        duration = SnackbarDuration.Indefinite,
//                    )
                }
            }

            Scaffold(
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.onBackground,
                contentWindowInsets = WindowInsets(0, 0, 0, 0),
                snackbarHost = { SnackbarHost(snackbarHostState) }
            ) { padding ->
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .consumeWindowInsets(padding)
                        .safeDrawingPadding()
                        .windowInsetsPadding(
                            WindowInsets.safeDrawing.only(
                                WindowInsetsSides.Horizontal,
                            ),
                        )
                ) {
                    MonsterdexNavHost(appState = appState)
                }
            }
        }
    }
}