package com.colisa.monsterdex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.colisa.monsterdex.core.data.utils.NetworkMonitor
import com.colisa.monsterdex.ui.MonsterdexApp
import com.colisa.monsterdex.ui.rememberMosterdexAppState
import com.colisa.mosterdex.core.design_system.theme.MonsterdexTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var networkMonitor: NetworkMonitor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MonsterdexTheme {
                MonsterdexApp(appState = rememberMosterdexAppState(networkMonitor = networkMonitor))
            }
        }
    }
}