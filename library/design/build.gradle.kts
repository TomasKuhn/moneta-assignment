apply<AndroidLibraryPlugin>()
apply<ComposePlugin>()

dependencies {
    implementation(projects.library.localization)
    implementation(libs.androidx.material3)
    implementation(platform(libs.androidx.compose.bom))
}
