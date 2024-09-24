apply<AndroidFeaturePlugin>()

dependencies {
    implementation(projects.library.mvvm)
    implementation(projects.library.networking)
    implementation(projects.library.test)
    implementation(projects.library.useCase)
    
    testImplementation(libs.bundles.test)
}

