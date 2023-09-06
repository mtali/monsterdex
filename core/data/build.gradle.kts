import com.colisa.monsterdex.Configuration

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kotlinKapt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.colisa.monsterdex.core.data"
    compileSdk = Configuration.compileSdk

    defaultConfig {
        minSdk = Configuration.minSdk
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    // Modules
    api(project(":core:model"))
    implementation(project(":core:network"))
    implementation(project(":core:database"))

    // Coroutines
    implementation(libs.kotlinx.coroutines.android)

    // Network
    implementation(libs.sandwich)

    // Hilt (DI)
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
}