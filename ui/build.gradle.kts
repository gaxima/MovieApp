plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.sonarqube")
}

android {
    namespace = "${AppConfig.applicationId}.ui"
    compileSdk = AppConfig.compileSdkVersion

    defaultConfig {
        minSdk = AppConfig.minSdkVersion

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}


sonar{
    properties{
        property("sonar.projectKey", "gaxima_MovieApp")
        property("sonar.organization", "gaxima")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}

dependencies {

    implementation(Libs.Kotlin.coreKtxVersion())
    implementation(Libs.Kotlin.getAppCompact())
    api(Libs.Material.getMaterialVersion())

    api(Libs.Activity.getActivityComposeVersion())
    api(Libs.Compose.getComposeUIVersion())
    api(Libs.Compose.getComposeToolingPreviewVersion())
    api(Libs.Compose.getComposeMaterialVersion())
    api(Libs.Compose.getComposeToolingVersion())
    api(Libs.Compose.getLifeCycleComposeRuntimeVersion())
    debugImplementation(Libs.Compose.getComposeUiTestManifestVersion())
    api(Libs.FlowLayout.getFlowVersion())
    implementation("com.google.android.material:material:1.12.0")

    api(Libs.Coil.getCoilVersion())
}