plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
    apply { id("com.google.dagger.hilt.android") }
}

android {
    namespace = "com.example.network"
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
    implementation(project(":data"))
    ksp(libs.bundles.ksp)
    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.junitExt)
    androidTestImplementation(libs.espresso.core)
    implementation(libs.bundles.imps)

    implementation(libs.retrofit)
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.retrofit.moshi.converter)
    implementation(libs.moshi)
}