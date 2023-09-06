import com.colisa.monsterdex.Configuration

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kotlinParcelize)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.colisa.monsterdex.core.model"
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
    // JSON Parsing
    implementation(libs.moshi.kotlin)
    ksp(libs.moshi.kotlin.codegen)
}