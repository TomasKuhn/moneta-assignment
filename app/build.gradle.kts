apply<AndroidAppPlugin>()

dependencies {
    implementation(projects.feature.players)
    implementation(projects.library.design)
    implementation(projects.library.localization)
    implementation(projects.library.mvvm)
    implementation(projects.library.networking)
    implementation(projects.library.useCase)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.navigation)
    implementation(libs.koin)

    debugImplementation(libs.androidx.ui.tooling)
}
