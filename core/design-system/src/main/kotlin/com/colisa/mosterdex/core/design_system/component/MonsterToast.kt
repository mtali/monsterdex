package com.colisa.mosterdex.core.design_system.component

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import java.util.UUID


data class ToastMessage(val id: String, val message: String)

fun Context.showToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun newToast(text: String): ToastMessage {
    return ToastMessage(id = UUID.randomUUID().toString(), message = text)
}

@Composable
fun Context.ShowToast(message: ToastMessage?, clear: () -> Unit) {
    LaunchedEffect(message) {
        if (message != null) {
            this@ShowToast.showToast(text = message.message)
            clear()
        }

    }
}