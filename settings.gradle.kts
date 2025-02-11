rootProject.name = "Productivity"

pluginManagement {
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id == "com.github.johnrengelman.shadow") {
                useModule("com.github.johnrengelman:shadow:8.1.1")
            }
        }
    }
}

