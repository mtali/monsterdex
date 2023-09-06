import com.colisa.monsterdex.Configuration

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlinKapt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.colisa.monsterdex.core.network"
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
    implementation(project(":core:model"))

    // Coroutines
    implementation(libs.kotlinx.coroutines.android)

    // Network
    implementation(libs.sandwich)
    implementation(libs.retrofit)
    implementation(libs.converter.moshi)
    implementation(platform(libs.okhttp.boom))
    implementation(libs.okhttp)
    implementation(libs.okhttp.interceptor)

    // Moshi (JSON Parsing)
    implementation(libs.moshi.kotlin)
    ksp(libs.moshi.kotlin.codegen)

    // Hilt (DI)
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    // Logging
    implementation(libs.timber)
}