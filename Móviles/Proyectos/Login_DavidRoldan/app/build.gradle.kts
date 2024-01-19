plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.example.login_davidroldan"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.login_davidroldan"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        javaCompileOptions {
            annotationProcessorOptions {
                arguments["room.schemaLocation"] = "$projectDir/schemas".toString()
            }
        }
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        viewBinding = true
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    implementation(libs.coreKtx.libr)
    implementation(libs.appcompat.libr)
    implementation(libs.material.libr)
    implementation(libs.constraintLayout.libr)
    implementation(libs.navigationFragmentKtx.libr)
    implementation(libs.navigationUiKtx.libr)
    testImplementation(libs.junit.libr)
    androidTestImplementation(libs.androidTestExtJunit.libr)
    androidTestImplementation(libs.espressoCore.libr)

    implementation(libs.datastorePreferences.libr)

    //Lifecycle libraries
    implementation(libs.lifecycleViewModelKtx.libr)
    implementation(libs.lifecycleLivedataKtx.libr)
    implementation(libs.lifecycleRuntimeKtx.libr)

    implementation(libs.recyclerviewSwipedecorator.libr)

    //by Viewmodel
    implementation(libs.fragmentKtx.libr)

    //Retrofit
    implementation(libs.retrofit.libr)
    implementation(libs.converterGson.libr)
    implementation(libs.converterMoshi.libr)
    implementation(libs.loggingInterceptor.libr)
    implementation(libs.converterScalars.libr)

    //Dagger
    implementation(libs.hiltAndroid.libr)
    kapt(libs.hiltAndroidCompiler.libr)

    //Timber
    implementation(libs.timber.libr)

    // Room libraries
    implementation(libs.roomRuntime.libr)
    kapt(libs.roomCompiler.libr)
    implementation(libs.roomKtx.libr)

    //Faker
    implementation(libs.javafaker.libr)
}


kapt {
    correctErrorTypes = true
}