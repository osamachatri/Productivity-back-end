val h2_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val postgres_version: String by project

plugins {
    kotlin("jvm") version "2.1.10"
    id("io.ktor.plugin") version "3.0.3"
    id("org.jetbrains.kotlin.plugin.serialization") version "2.1.10"
    id("com.gradleup.shadow") version "8.3.5"
}

group = "com.oussama_chatri"
version = "0.0.1"

application {
    mainClass.set("com.oussama_chatri.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

tasks {
    shadowJar {
        archiveBaseName.set("ktor-app")
        archiveClassifier.set("")
        archiveVersion.set("")
        mergeServiceFiles()
    }
}

ktor {
    fatJar {
        archiveFileName.set("fat.jar")
    }
}

tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
    archiveBaseName.set("ktor-app")
    archiveClassifier.set("")
    archiveVersion.set("")
    manifest {
        attributes["Main-Class"] = "com.oussama_chatri.ApplicationKt"
    }
    mergeServiceFiles()
}

//tasks {
//    named<com.gradleup.shadow.tasks.ShadowJar>("shadowJar") {
//        archiveBaseName.set("ktor-app")
//        archiveClassifier.set("")
//        archiveVersion.set("")
//        mergeServiceFiles()
//    }
//}


repositories {
    mavenCentral()
}


dependencies {
    implementation("io.ktor:ktor-server-core")
    implementation("io.ktor:ktor-server-auth")
    implementation("io.ktor:ktor-server-auth-jwt")
    implementation("io.ktor:ktor-server-content-negotiation")
    implementation("io.ktor:ktor-serialization-kotlinx-json")
    implementation("org.postgresql:postgresql:$postgres_version")
    implementation("com.h2database:h2:$h2_version")
    implementation("io.ktor:ktor-server-netty")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("io.ktor:ktor-server-config-yaml")
    testImplementation("io.ktor:ktor-server-test-host")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
    implementation("org.ktorm:ktorm-core:4.1.1")
    implementation("org.mindrot:jbcrypt:0.4")
    implementation("com.mysql:mysql-connector-j:9.2.0")
    implementation("io.ktor:ktor-server-auth:$kotlin_version")
    implementation("io.ktor:ktor-server-auth-jwt:$kotlin_version")
    // JSON Serialization
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.6")
    implementation("io.ktor:ktor-server-content-negotiation:2.3.6")

    // Authentication
    implementation("io.ktor:ktor-server-auth:2.3.6")
    implementation("io.ktor:ktor-server-auth-jwt:2.3.6")
}

configurations.all {
    resolutionStrategy {
        force("com.github.johnrengelman:shadow:8.1.1")
    }
}