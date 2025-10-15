plugins {
    id("java")
}

group = "de.mcmdev"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("space.arim.dazzleconf:dazzleconf-core:2.0.0-M1")
    implementation("space.arim.dazzleconf:dazzleconf-yaml:2.0.0-M1")
    implementation("space.arim.injector:injector:1.1.0-RC2")
    compileOnly("jakarta.inject:jakarta.inject-api:2.0.1")
}

repositories {
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

dependencies {
    implementation("io.papermc.paper:paper-api:1.21.8-R0.1-SNAPSHOT")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}