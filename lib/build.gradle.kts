plugins {
    // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
    kotlin("jvm") version "1.6.20"

    // Apply the org.jetbrains.kotlin.plugin.serialization Plugin
    kotlin("plugin.serialization") version "1.6.20"

    // Maven Publish plugin - https://docs.gradle.org/current/userguide/publishing_maven.html
    id("maven-publish")

    // Apply the java-library plugin for API and implementation separation.
    `java-library`
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    // Use the Kotlin JDK 8 standard library.
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // Used to send http requests
    api("com.squareup.okhttp3", "okhttp", "4.9.3")

    // Used for json serialization
    api("org.jetbrains.kotlinx", "kotlinx-serialization-json", "1.3.2")
}

java {
    withSourcesJar()
    withJavadocJar()
}

tasks.jar {
    manifest {
        attributes(
            "Implementation-Title" to "discord-webhooks",
            "Implementation-Version" to "1.0"
        )
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "me.binarywriter"
            artifactId = "discord-webhooks"
            version = "1.0"

            from(components["java"])
        }
    }
}
