rootProject.name = "mars-rover-kata"

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            library("kotest", "io.kotest", "kotest-assertions-core").version("5.1.0")
            library("junit5-bom", "org.junit", "junit-bom").version("5.8.2")
        }
    }
}
