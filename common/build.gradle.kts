import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.compose.compose

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("com.android.library")
    id("org.jetbrains.kotlin.plugin.serialization")
}

group = "io.kraftsman"
version = "1.0-SNAPSHOT"

kotlin {
    android()
    jvm("desktop") {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)
                @OptIn(ExperimentalComposeLibrary::class)
                api(compose.material3)
                api(compose.materialIconsExtended)

                implementation("io.ktor:ktor-client-core:2.1.2")
                implementation("io.ktor:ktor-client-cio-jvm:2.1.2")
                implementation("io.ktor:ktor-client-content-negotiation:2.1.2")
                implementation("io.ktor:ktor-serialization-kotlinx-json:2.1.2")

                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.0")
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                api("androidx.appcompat:appcompat:1.2.0")
                api("androidx.core:core-ktx:1.3.1")

                api("androidx.compose.foundation:foundation:1.2.1")
                api("androidx.compose.material3:material3:1.0.0-beta03")
                api("androidx.compose.material:material:1.2.1")
                api("androidx.compose.material:material-icons-extended:1.2.1")
                api("androidx.compose.ui:ui-tooling:1.2.1")
                api("androidx.compose.ui:ui-tooling-preview:1.2.1")
                api("androidx.compose.ui:ui:1.2.1")
                api("androidx.compose.runtime:runtime:1.2.1")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation("junit:junit:4.13")
            }
        }
        val desktopMain by getting {
            dependencies {
                api(compose.preview)
            }
        }
        val desktopTest by getting
    }
}

android {
    compileSdk = 33
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 29
        targetSdk = 33
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}