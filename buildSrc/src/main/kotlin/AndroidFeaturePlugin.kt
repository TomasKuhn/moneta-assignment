import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply

class AndroidFeaturePlugin : Plugin<Project> by local plugin {
    apply<AndroidLibraryPlugin>()
    apply<ComposePlugin>()
}
