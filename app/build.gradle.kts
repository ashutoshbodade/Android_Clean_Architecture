plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.ashutosh.androidcleanarchitecture"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ashutosh.androidcleanarchitecture"
        minSdk = 26
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    testImplementation ("org.mockito:mockito-core:5.11.0")
    androidTestImplementation ("org.mockito:mockito-android:5.11.0")
    androidTestImplementation ("androidx.paging:paging-testing:3.2.1")

    implementation("com.google.dagger:hilt-android:2.51")
    kapt("com.google.dagger:hilt-android-compiler:2.51")

    val retrofit_version = "2.9.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofit_version")
    implementation("com.squareup.retrofit2:converter-gson:$retrofit_version")
    implementation("com.squareup.okhttp3:okhttp:4.11.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")

    val paging_version = "3.2.1"
    implementation("androidx.paging:paging-runtime:$paging_version")

    implementation ("com.intuit.sdp:sdp-android:1.1.0")
    implementation ("com.intuit.ssp:ssp-android:1.1.0")

    val room_version = "2.6.1"
    implementation ("androidx.room:room-runtime:$room_version")
    implementation ("androidx.room:room-ktx:$room_version")
    implementation ("androidx.room:room-paging:$room_version")
    kapt ("androidx.room:room-compiler:$room_version")

    implementation ("com.github.bumptech.glide:glide:4.16.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.16.0")

}

kapt {
    correctErrorTypes = true
}