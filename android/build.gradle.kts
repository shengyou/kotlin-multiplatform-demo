plugins {
    id("org.jetbrains.compose")
    id("com.android.application")
    kotlin("android")
}

group "io.kraftsman"
version "1.0-SNAPSHOT"

dependencies {
    implementation(project(":common"))

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.activity:activity-compose:1.6.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1")

    api("androidx.compose.foundation:foundation:1.2.1")
    api("androidx.compose.material3:material3:1.0.0-beta03")
    api("androidx.compose.material:material:1.2.1")
    api("androidx.compose.material:material-icons-extended:1.2.1")
    debugApi("androidx.compose.ui:ui-tooling:1.2.1")
    api("androidx.compose.ui:ui-tooling-preview:1.2.1")
    api("androidx.compose.ui:ui:1.2.1")
    api("androidx.compose.runtime:runtime:1.2.1")

    implementation("androidx.compose.ui:ui-tooling-preview:1.2.1")
    debugImplementation("androidx.compose.ui:ui-tooling:1.2.1")

    api("io.ktor:ktor-client-core:2.1.2")
    implementation("io.ktor:ktor-client-okhttp:2.1.2")
    implementation("io.ktor:ktor-client-content-negotiation:2.1.2")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.1.2")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.2.1")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.2.1")
}

android {
    compileSdk = 33
    defaultConfig {
        applicationId = "io.kraftsman.android"
        minSdk = 29
        targetSdk = 33
        versionCode = 1
        versionName = "1.0-SNAPSHOT"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}