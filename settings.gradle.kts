@file:Suppress("UnstableApiUsage")


pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Monsterdex"
include(":app")
include(":core:model")
include(":core:database")
include(":core:network")
include(":core:data")
include(":core:design-system")
include(":core:test")
include(":feature:pokemons")
include(":feature:pokemon-detail")