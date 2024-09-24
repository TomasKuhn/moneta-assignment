plugins {
    id("com.android.library")
}

apply<AndroidLibraryPlugin>()

dependencies {
    implementation(libs.junit)
    implementation(libs.kotlin.coroutines.test)
}
