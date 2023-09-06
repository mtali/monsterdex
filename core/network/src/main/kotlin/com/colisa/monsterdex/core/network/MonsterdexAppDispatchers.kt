package com.colisa.monsterdex.core.network


import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val dispatchers: MonsterdexAppDispatchers)

enum class MonsterdexAppDispatchers {
    IO
}
