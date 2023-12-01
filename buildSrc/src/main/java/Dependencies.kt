import org.gradle.api.artifacts.dsl.DependencyHandler

object Dependencies {
    // Hilt
    const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"
    const val hiltNavigation = "androidx.hilt:hilt-navigation-compose:${Versions.hiltNavigation}"

    // Retrofit
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"

    // Compose
    const val composeMaterial3 = "androidx.compose.material3:material3:${Versions.composeMaterial3}"
    const val composeBom = "androidx.compose:compose-bom:2023.10.01"
    const val composeNavigation =
        "androidx.navigation:navigation-compose:${Versions.composNavigation}"
    const val composeUiToolingPreview =
        "androidx.compose.ui:ui-tooling-preview-android:${Versions.compose}"
    const val composeLifeCycleRuntime =
        "androidx.lifecycle:lifecycle-runtime-compose:${Versions.compose}"

    // Coil
    const val coil = "io.coil-kt:coil-compose:${Versions.coil}"

    // Room
    const val room = "androidx.room:room-ktx:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"

    // Test
    const val testJUnit = "junit:junit:${Versions.jUnit}"
    const val androidTestJUnit = "androidx.test.ext:junit:${Versions.androidJUnit}"
    const val androidTestEspresso =
        "androidx.test.espresso:espresso-core:${Versions.androidEspresso}"
}

fun DependencyHandler.hilt() {
    implementation(Dependencies.hiltAndroid)
    kapt(Dependencies.hiltCompiler)
    implementation(Dependencies.hiltNavigation)
}

fun DependencyHandler.retrofit() {
    implementation(Dependencies.retrofit)
    implementation(Dependencies.gson)
    implementation(Dependencies.okHttp)
}

fun DependencyHandler.compose() {
    implementation(Dependencies.composeMaterial3)
    implementation(Dependencies.composeNavigation)
    implementation(Dependencies.composeUiToolingPreview)
    implementation(Dependencies.composeLifeCycleRuntime)
    implementation(platform(Dependencies.composeBom))
}

fun DependencyHandler.coil() {
    implementation(Dependencies.coil)
}

fun DependencyHandler.room() {
    implementation(Dependencies.room)
    kapt(Dependencies.roomCompiler)
}

fun DependencyHandler.test() {
    test(Dependencies.testJUnit)
    androidTest(Dependencies.androidTestJUnit)
    androidTest(Dependencies.androidTestEspresso)
}