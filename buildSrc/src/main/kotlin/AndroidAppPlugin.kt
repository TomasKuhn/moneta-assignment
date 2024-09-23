import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply

class AndroidAppPlugin : Plugin<Project> by local plugin {
    apply(plugin = "com.android.application")
    apply(plugin = "kotlin-android")
    apply<CompilersPlugin>()

    androidApplication {
        compileSdk = ProjectConfiguration.Sdk.compile
        namespace = namespace()

        defaultConfig {
            applicationId = ProjectConfiguration.namespaceId
            minSdk = ProjectConfiguration.Sdk.min
            targetSdk = ProjectConfiguration.Sdk.target
            versionCode = ProjectConfiguration.versionCode
            versionName = ProjectConfiguration.versionName

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
        
        buildTypes {
            release {
                isMinifyEnabled = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }

        buildFeatures {
            compose = true
        }
        composeOptions {
            kotlinCompilerExtensionVersion = version("composeCompiler")
        }
    }
}
