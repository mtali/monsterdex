package com.colisa.monsterdex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import com.colisa.mosterdex.core.design_system.component.MonsterdexBackground
import com.colisa.mosterdex.core.design_system.component.MonsterdexGradientBackground
import com.colisa.mosterdex.core.design_system.theme.MonsterdexTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MonsterdexTheme {
                MonsterdexBackground {
                    MonsterdexGradientBackground {
                        Text(text = "Mtali")
                    }
                }
            }
        }
    }
}