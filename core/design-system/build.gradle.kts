import com.colisa.monsterdex.Configuration

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = "com.colisa.mosterdex.core.design_system"
    compileSdk = Configuration.compileSdk

    defaultConfig {
        minSdk = Configuration.minSdk
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
}

dependencies {
    // Compose + M3
    val composeBom = platform(libs.compose.bom)
    implementation(composeBom)
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling)
    implementation(libs.ui.tooling.preview)

    implementation(libs.material3)
    implementation(libs.material3.window)

    // Color picker
    implementation(libs.palette.ktx)

    // Coil
    implementation(libs.coil.kt.compose)

    implementation(libs.core.ktx)
}