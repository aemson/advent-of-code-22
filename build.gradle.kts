plugins {
    kotlin("jvm") version "1.7.22"
    id("pl.allegro.tech.build.axion-release") version "1.14.3"
}
version = scmVersion.version

repositories {
    mavenCentral()
}

tasks {
    sourceSets {
        main {
            java.srcDirs("src")
        }
    }

    wrapper {
        gradleVersion = "7.6"
    }
}
