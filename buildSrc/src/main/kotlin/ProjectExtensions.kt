import ProjectConfiguration.namespaceId
import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.findByType
import org.jetbrains.kotlin.gradle.dsl.KotlinProjectExtension

/** Applies block to Project specific part of [T] type **/
internal inline fun <reified T : Any> Project.extension(block: T.() -> Unit) {
    extensions.findByType<T>()?.apply(block)
}

/** Applies block to Project specific part of [ApplicationExtension] type **/
internal fun Project.androidApplication(block: ApplicationExtension.() -> Unit) {
    extension(block)
}

/** Applies block to Project specific part of [LibraryExtension] type **/
fun Project.android(block: LibraryExtension.() -> Unit) {
    extension(block)
}

/**
Applies block to Project specific part of [KotlinProjectExtension] type
 */
internal fun Project.kotlin(block: KotlinProjectExtension.() -> Unit) {
    extension(block)
}

@Suppress("ClassName")
/** Helper object for sugar extensions **/
internal object local

/** Syntactic sugar for Project specification configuration **/
internal infix fun local.plugin(config: Project.() -> Unit) = Plugin<Project> { config(it) }

/**
 * Module namespace in format [namespaceId].{module name without dashes}
 */
fun Project.namespace() = "$namespaceId.${name.replace("-", "")}"

fun Project.version(name: String) =
    extensions.findByType<VersionCatalogsExtension>()
        ?.named("libs")?.findVersion(name)?.get()?.requiredVersion.orEmpty()



