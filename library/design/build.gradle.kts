apply<AndroidLibraryPlugin>()
apply<ComposePlugin>()

dependencies {
    implementation(libs.androidx.material3)
    implementation(platform(libs.androidx.compose.bom))
}
