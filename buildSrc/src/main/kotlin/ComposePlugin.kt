import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class ComposePlugin : Plugin<Project> by local plugin {
    android {
        buildFeatures.compose = true
        composeOptions {
            kotlinCompilerExtensionVersion = libs.findVersion("composeCompiler").get().requiredVersion
        }
    }

    androidApplication {
        buildFeatures.compose = true
        composeOptions {
            kotlinCompilerExtensionVersion = libs.findVersion("composeCompiler").get().requiredVersion
        }
    }

    dependencies {
        implementation(libs.dependency("androidx.activity.compose"))
        implementation(platform(libs.dependency("androidx.compose.bom")))
        implementation(libs.dependency("androidx.ui"))
        implementation(libs.dependency("androidx.ui.graphics"))
        implementation(libs.dependency("androidx.ui.tooling.preview"))
        implementation(libs.dependency("androidx.material3"))
    }
}
