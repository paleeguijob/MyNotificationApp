@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.androidx)
    alias(libs.plugins.daggerHilt)
    alias(libs.plugins.kspCompiler)
}

android {
    namespace = "aof.rp.mynotificationapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "aof.rp.mynotificationapp"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.profileinstaller)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.navigation.common)

    //Unit Test
    testImplementation(libs.junit)
    testImplementation(libs.junit5)
    testImplementation (libs.mockk)
    testImplementation(libs.arch.core.test)
    testImplementation(libs.coroutine.test)
    testImplementation(libs.hamcrest.test)
    testImplementation(libs.androidx.core.ktx.test)
    implementation(libs.androidx.junit.ktx)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //Dagger Hilt
    implementation(libs.android.hilt)
    ksp(libs.android.hilt.compiler)
    testImplementation(libs.hilt.android.testing)
    kspTest(libs.android.hilt.compiler)
    testAnnotationProcessor(libs.android.hilt.compiler)

    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson.converter)

    //Glide
    implementation(libs.gilde)
}