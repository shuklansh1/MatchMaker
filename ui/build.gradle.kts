plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose)
    alias(libs.plugins.ksp)
    apply { id("com.google.dagger.hilt.android") }

    kotlin("plugin.serialization") version "2.1.20"
}

android {
    namespace = "com.example.ui"
    compileSdk = 35

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(project (":domain"))

    implementation("com.squareup.picasso:picasso:2.8")
    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.foundation.android)
    ksp(libs.bundles.ksp)
    implementation(libs.bundles.imps)
    implementation(libs.bundles.compose)
    implementation(libs.bundles.coil)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.recyclerview)


    testImplementation(libs.junit)
    androidTestImplementation(libs.junitExt)
    androidTestImplementation(libs.espresso.core)
}