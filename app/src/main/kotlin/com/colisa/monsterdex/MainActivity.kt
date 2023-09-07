package com.colisa.monsterdex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.colisa.monsterdex.ui.MonsterdexApp
import com.colisa.mosterdex.core.design_system.theme.MonsterdexTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MonsterdexTheme {
                MonsterdexApp()
            }
        }
    }
}