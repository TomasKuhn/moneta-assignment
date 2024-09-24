apply<AndroidAppPlugin>()

dependencies {
    implementation(projects.feature.players)
    implementation(projects.library.design)
    implementation(projects.library.mvvm)
    implementation(projects.library.networking)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.navigation)
    implementation(libs.koin)

    testImplementation(libs.junit)

    debugImplementation(libs.androidx.ui.tooling)
}
