pluginManagement {
    repositories{
        gradlePluginPortal()
        mavenCentral()
        jcenter()
    }
    resolutionStrategy {
        eachPlugin {
            when(requested.id.id){
                "org.jetbrains.dokka" -> useModule("org.jetbrains.dokka:dokka-gradle-plugin:0.9.17")
            }
        }
    }
}

rootProject.name = "KRL"

