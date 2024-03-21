plugins {
    id ("com.android.library")
    id ("org.jetbrains.kotlin.android")
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
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
    debugImplementation (Libs.Compose.getComposeUiTestManifestVersion())
    api (Libs.FlowLayout.getFlowVersion())
    implementation("com.google.android.material:material:1.11.0")


    api(Libs.Coil.getCoilVersion())
}