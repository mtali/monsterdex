<h1 align="center">Monsterdex</h1>


<p align="center">  
🗡️ 
Mosterdex exemplifies modern Android development with Hilt, Coroutines, Flow, Room, ViewModel, and Material Design, all structured within an MVVM architecture.
</p>

</br>

<p align="center">
<img src="/previews/landing.png"/>
</p>

## Tech Stack & Open-Source Libraries
- Minimum SDK level: 24
- Developed
  with [Kotlin](https://kotlinlang.org/), [Coroutines](https://github.com/Kotlin/kotlinx.coroutines)
  and [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/)
  for asynchronous programming.
- Jetpack
  -[Lifecycle](https://developer.android.com/jetpack/androidx/releases/lifecycle): Observe Android
  lifecycles and handle UI states upon lifecycle changes.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel): Manages
    UI-related data holder and is lifecycle aware, allowing data to survive configuration changes
    such
    as screen rotations.
  - [Room](https://developer.android.com/training/data-storage/room): provides an abstraction layer
    over SQLite.
  - [Compose](https://developer.android.com/jetpack/compose):  Android’s recommended modern toolkit
    for building native UI
  - [Hilt](https://dagger.dev/hilt/): Used for dependency injection.
  -
- Architecture
  - MVVM Architecture (Compose - ViewModel - Model)
  - Repository Pattern
- [Compose Navigation](https://developer.android.com/jetpack/compose/navigation): Provides
  navigation support for Jetpack Compose applications
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit): Used to construct REST APIs and handle
  paging network data.
- [Sandwich](https://github.com/skydoves/Sandwich): Provides a lightweight and modern response
  interface to handle network payloads on Android.
- [Moshi](https://github.com/square/moshi/): A modern JSON library for Kotlin and Java.
- [ksp](https://github.com/google/ksp): Kotlin Symbol Processing API.
- [Turbine](https://github.com/cashapp/turbine): A small testing library for kotlinx.coroutines
  Flow.
- [Material3](https://m3.material.io/): Material 3 is the latest version of Google’s open-source
  design system
- [Coil](https://github.com/coil-kt/coil): An image loading library for Android backed by Kotlin
  Coroutines
- [Compose Animation](https://developer.android.com/jetpack/compose/animation/introduction):
  Powerful and extensible APIs that make it easy to implement various animations in your app's UI
- [Timber](https://github.com/JakeWharton/timber): A logger with a small, extensible API.

## Architecture

**Monsterdex** is based on the MVVM architecture and the Repository pattern, which follows
the [Google's official architecture guidance](https://developer.android.com/topic/architecture).


