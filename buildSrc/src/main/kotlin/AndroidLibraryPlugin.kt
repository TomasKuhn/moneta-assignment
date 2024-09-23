import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class AndroidLibraryPlugin : Plugin<Project> by local plugin {
    apply(plugin = "com.android.library")
    apply(plugin = "kotlin-android")
    apply<CompilersPlugin>()

    android {
        compileSdk = ProjectConfiguration.Sdk.compile
        namespace = namespace()

        defaultConfig {
            minSdk = ProjectConfiguration.Sdk.min
        }
    }
    
    dependencies { 
        implementation(libs.dependency("koin"))
    }
}
