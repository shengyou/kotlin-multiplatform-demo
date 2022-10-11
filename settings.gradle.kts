// Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }

    plugins {
        kotlin("jvm").version(extra["kotlin.version"] as String)
        kotlin("multiplatform").version(extra["kotlin.version"] as String)

        id("org.jetbrains.kotlin.plugin.serialization").version(extra["kotlin.version"] as String)

        kotlin("android").version(extra["kotlin.version"] as String)
        id("com.android.application").version(extra["agp.version"] as String)
        id("com.android.library").version(extra["agp.version"] as String)

        id("org.jetbrains.compose").version(extra["compose.version"] as String)
    }
}

rootProject.name = "compose-multiplatform-demo"

include(":android")
include(":desktop")
include(":common")
include(":server")
