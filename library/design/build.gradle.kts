apply<AndroidLibraryPlugin>()
apply<ComposePlugin>()

dependencies {
    implementation(projects.library.localization)
}
