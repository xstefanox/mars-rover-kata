rootProject.name = "mars-rover-kata"

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            version("junit5", "5.8.2")
            library("kotest", "io.kotest", "kotest-assertions-core").version("5.1.0")
            library("junit5-bom", "org.junit", "junit-bom").version("junit5")
            library("junit5-params", "org.junit.jupiter", "junit-jupiter-params").versionRef("junit5")
        }
    }
}
