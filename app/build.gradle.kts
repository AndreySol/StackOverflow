plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.stackoverflow"
    compileSdk = 34

    lint {
        checkAllWarnings = true
        checkDependencies = true
    }

    defaultConfig {
        applicationId = "com.example.stackoverflow"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
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
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}
dependencies {
    implementation(project(":feature:auth"))
    implementation(project(":feature:questions"))
    implementation(project(":feature:answers"))
    implementation(project(":common"))
    implementation(project(":network"))
    implementation(project(":database"))
    implementation(project(":data"))

    //compose()
    implementation(libs.composeMaterial3)
    implementation(libs.composeNavigation)
    implementation(libs.composeUiToolingPreview)
    implementation(libs.composeLifeCycleRuntime)
    implementation(platform(libs.composeBom))

    //hilt()
    implementation(libs.hiltAndroid)
    kapt(libs.hiltCompiler)
    implementation(libs.hiltNavigation)

    //test()
    testImplementation(libs.testJUnit)
    androidTestImplementation(libs.androidTestJUnit)
    androidTestImplementation(libs.androidTestEspresso)
}