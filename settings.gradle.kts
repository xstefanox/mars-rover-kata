rootProject.name = "mars-rover-kata"

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            library("kotest", "io.kotest", "kotest-assertions-core").version("5.1.0")
        }
    }
}
