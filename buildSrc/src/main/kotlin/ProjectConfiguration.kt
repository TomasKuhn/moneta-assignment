import org.gradle.api.JavaVersion

object ProjectConfiguration {
    const val namespaceId = "cz.kuhnt.moneta.assignment"
    val targetJvmVersion = JavaVersion.VERSION_17.majorVersion.toInt()
    const val versionCode = 1
    const val versionName = "0.1"
    
    object Sdk {
        const val min = 24
        const val target = 34
        const val compile = 34
    }
}
