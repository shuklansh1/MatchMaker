plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose)
    alias(libs.plugins.ksp)
    apply { id("com.google.dagger.hilt.android") }
}

android {
    namespace = "com.example.matchmaker"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.matchmaker"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    packaging {
        resources {
            excludes += "META-INF/gradle/incremental.annotation.processors"
        }
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":ui"))
    implementation(project(":network"))

    implementation(libs.bundles.compose)
    implementation(libs.multidex)
    implementation(libs.bundles.ktxLibs)
    debugImplementation(libs.bundles.debugLibs)

    implementation(libs.bundles.lifecycle)
    implementation(libs.bundles.imps)
    ksp(libs.bundles.ksp)
    implementation(libs.bundles.coil)
    implementation(libs.retrofit)
    implementation(libs.bundles.analytics)


    implementation(libs.app.review)
    implementation(libs.kotlinx.serialization.json)

    testImplementation(libs.bundles.unitTest)
    testImplementation(libs.bundles.appUnitTest)
    androidTestImplementation(libs.bundles.uiTest)
}