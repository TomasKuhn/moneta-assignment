plugins {
    id("com.android.library")
}

apply<AndroidLibraryPlugin>()

dependencies {
    implementation(projects.library.useCase)
    implementation(libs.junit)
    implementation(libs.kotlin.coroutines.test)
    implementation(libs.mockk)
}
