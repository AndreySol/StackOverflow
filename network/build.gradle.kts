plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.stackoverflow.api"
    compileSdk = 33

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
}

dependencies {
    implementation(project(":common"))
    implementation(project(":feature:questions"))
    implementation(project(":feature:questions"))

    //retrofit()
    implementation(libs.retrofit)
    implementation(libs.gson)
    implementation(libs.okHttp)

    //hilt()
    implementation(libs.hiltAndroid)
    kapt(libs.hiltCompiler)
    implementation(libs.hiltNavigation)

    //test()
    testImplementation(libs.testJUnit)
    androidTestImplementation(libs.androidTestJUnit)
    androidTestImplementation(libs.androidTestEspresso)
}