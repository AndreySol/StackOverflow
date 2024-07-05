plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.stackoverflow.answers"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_18
        targetCompatibility = JavaVersion.VERSION_18
    }
    kotlinOptions {
        jvmTarget = "18"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
}

dependencies {
    implementation(project(":common"))
    implementation(project(":feature:questions"))

    //hilt()
    implementation(libs.hiltAndroid)
    kapt(libs.hiltCompiler)
    implementation(libs.hiltNavigation)

    //compose()
    implementation(libs.composeMaterial3)
    implementation(libs.composeNavigation)
    implementation(libs.composeUiToolingPreview)
    implementation(libs.composeLifeCycleRuntime)
    implementation(platform(libs.composeBom))

    //coil()
    implementation(libs.coil)

    // Material
    implementation(libs.material)

    //test()
    testImplementation(libs.testJUnit)
    androidTestImplementation(libs.androidTestJUnit)
    androidTestImplementation(libs.androidTestEspresso)

    debugImplementation(libs.composeUiTooling)
    debugImplementation(libs.composeUiTest)
}