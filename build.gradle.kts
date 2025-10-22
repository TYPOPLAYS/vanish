import xyz.jpenilla.runpaper.task.RunServer

plugins {
    id("java")
    id("com.gradleup.shadow") version "9.2.2"
    id("io.papermc.paperweight.userdev") version "2.0.0-beta.18"
    id("xyz.jpenilla.run-paper") version "3.0.0"
    id("xyz.jpenilla.resource-factory-paper-convention") version "1.3.1"
    id("xyz.jpenilla.gremlin-gradle") version "0.0.9"
}

group = "de.mcmdev"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
    maven {
        name = "helpchat"
        url = uri("https://repo.helpch.at/releases")
    }
}

dependencies {
    paperweight.paperDevBundle("1.21.8-R0.1-SNAPSHOT")
    implementation("space.arim.dazzleconf:dazzleconf-core:2.0.0-M1")
    implementation("space.arim.dazzleconf:dazzleconf-yaml:2.0.0-M1")
    implementation("space.arim.injector:injector:1.1.0-RC2")
    implementation("jakarta.inject:jakarta.inject-api:2.0.1")
    implementation("me.clip:placeholderapi:2.11.6")
}

paperweight.reobfArtifactConfiguration = io.papermc.paperweight.userdev.ReobfArtifactConfiguration.MOJANG_PRODUCTION

paperPluginYaml {
    main = "de.mcmdev.vanish.VanishPlugin"
    apiVersion = "1.21"
    foliaSupported = true
}

runPaper {
    folia {
        registerTask()
    }
}

tasks.getByName("runFolia", RunServer::class) {
    jvmArgs("-XX:+AllowEnhancedClassRedefinition")
}

tasks.assemble {
    dependsOn(tasks.writeDependencies)
}

configurations.compileOnly {
    extendsFrom(configurations.runtimeDownload.get())
}
configurations.testImplementation {
    extendsFrom(configurations.runtimeDownload.get())
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}