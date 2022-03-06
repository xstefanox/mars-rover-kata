rootProject.name = "mars-rover-kata"

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            version("junit5", "5.8.2")
            version("slf4j", "1.7.35")
            version("arrow", "1.0.1")
            library("coroutines-test", "org.jetbrains.kotlinx", "kotlinx-coroutines-test").version("1.6.0")
            library("kotest-assertions", "io.kotest", "kotest-assertions-core").version("5.1.0")
            library("kotest-assertions-arrow", "io.kotest.extensions", "kotest-assertions-arrow").version("1.2.1")
            library("junit5-bom", "org.junit", "junit-bom").version("junit5")
            library("junit5-params", "org.junit.jupiter", "junit-jupiter-params").versionRef("junit5")
            library("slf4j-api", "org.slf4j", "slf4j-api").versionRef("slf4j")
            library("slf4j-simple", "org.slf4j", "slf4j-simple").versionRef("slf4j")
            library("slf4j-bridge", "org.slf4j", "jul-to-slf4j").versionRef("slf4j")
            library("mockk", "io.mockk", "mockk").version("1.12.2")
            library("arrow-bom", "io.arrow-kt", "arrow-stack").versionRef("arrow")
            library("arrow", "io.arrow-kt", "arrow-core").versionRef("arrow")
            plugin("ktlint", "org.jlleitschuh.gradle.ktlint").version("10.2.1")
        }
    }
}
