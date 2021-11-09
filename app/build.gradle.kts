import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    kotlin("android")
    id("com.android.application")
    id("kotlin-parcelize")
    kotlin("plugin.serialization") version "1.5.31"
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

val composeVersion = "1.0.4"
val ktorVersion = "1.6.4"
val kotlinVersion = "1.5.31"
val hiltVersion = "2.38.1"
val lifecycleVersion = "2.4.0"

val properties = Properties()
properties.load(project.rootProject.file("local.properties").inputStream())

android {

    compileSdk = 31

    defaultConfig {
        applicationId = "it.github.samuele794.composedeepl"
        minSdk = 26
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
        buildConfigField("DEEPL_BASE_URL", """api-free.deepl.com""")
    }

    buildTypes {
        debug {
            buildConfigField("DEEPL_API_KEY", properties.getProperty("deepl.apikey.${name}"))
        }

        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            buildConfigField("DEEPL_API_KEY", properties.getProperty("deepl.apikey.${name}"))
        }

    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = composeVersion
    }

    packagingOptions {
        resources.excludes.add("/META-INF/{AL2.0,LGPL2.1}")
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.compose.ui:ui:${composeVersion}")
    implementation("androidx.compose.material:material:${composeVersion}")
    implementation("androidx.compose.ui:ui-tooling-preview:${composeVersion}")
    implementation("androidx.activity:activity-compose:1.4.0")
    testImplementation("junit:junit:4.+")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:${composeVersion}")
    debugImplementation("androidx.compose.ui:ui-tooling:${composeVersion}")

    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.0-rc01")

    // LIFECYCLE
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycleVersion")
    implementation("androidx.compose.runtime:runtime-livedata:1.1.0-beta02")

    // HTTP CLIENT
    implementation("io.ktor:ktor-client-core:${ktorVersion}")
    implementation("io.ktor:ktor-client-cio:${ktorVersion}")
    implementation("io.ktor:ktor-client-serialization:${ktorVersion}")
    implementation("io.ktor:ktor-client-logging:${ktorVersion}")

    //NAVIGATION
    implementation("androidx.navigation:navigation-compose:2.4.0-beta01")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0-alpha03")

    //HILT
    implementation("com.google.dagger:hilt-android:${hiltVersion}")
    kapt("com.google.dagger:hilt-compiler:${hiltVersion}")

//    implementation("androidx.compose.material3:material3:1.0.0-alpha01")
}


fun com.android.build.api.dsl.VariantDimension.buildConfigField(name: String, value: String) =
    buildConfigField("String", name, "\"$value\"")