import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
    application
    alias(libs.plugins.ktlint)
}

group = "io.github.xstefanox"
version = "SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.slf4j.api)
    implementation(libs.arrow)
    implementation(libs.coroutines)
    runtimeOnly(libs.slf4j.simple)
    testImplementation(kotlin("test"))
    testImplementation(platform(libs.junit5.bom))
    testImplementation(platform(libs.arrow.bom))
    testImplementation(libs.junit5.params)
    testImplementation(libs.mockk)
    testImplementation(libs.kotest.assertions)
    testImplementation(libs.kotest.assertions.arrow)
    testImplementation(libs.coroutines.test)
    testRuntimeOnly(libs.slf4j.bridge)
}

tasks.test {
    useJUnitPlatform()
    systemProperty("java.util.logging.config.file", "logging.properties")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
    kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
}

application {
    mainClass.set("MainKt")
}
