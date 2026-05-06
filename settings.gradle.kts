pluginManagement {
    repositories {
        maven("https://repo.essential.gg/repository/maven-releases/")
        maven("https://maven.architectury.dev/")
        maven("https://maven.fabricmc.net")
        maven("https://maven.minecraftforge.net/")
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "PaperFixes"

include("agent")