rootProject.name = "mars-rover-kata"

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            version("junit5", "5.8.2")
            version("slf4j", "1.7.35")
            library("kotest", "io.kotest", "kotest-assertions-core").version("5.1.0")
            library("junit5-bom", "org.junit", "junit-bom").version("junit5")
            library("junit5-params", "org.junit.jupiter", "junit-jupiter-params").versionRef("junit5")
            library("slf4j-api", "org.slf4j", "slf4j-api").versionRef("slf4j")
            library("slf4j-simple", "org.slf4j", "slf4j-simple").versionRef("slf4j")
            library("slf4j-bridge", "org.slf4j", "jul-to-slf4j").versionRef("slf4j")
        }
    }
}
